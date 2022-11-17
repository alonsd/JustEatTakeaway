package com.just_eat_take_away.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.data.repository.JustEatTakeawayRepository
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel
import com.just_eat_take_away.model.ui_models.DataSourceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val justEatTakeawayRepository: JustEatTakeawayRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    private val uiEvent = _uiEvent.asSharedFlow()

    private var reloadingDataInProgress = false

    init {
        observeUiEvents()
        getRestaurants()
    }

    private fun getRestaurants() = viewModelScope.launch {
        when (val response = justEatTakeawayRepository.getRestaurants()) {
            is NetworkResponse.Success -> {
                submitUiState(_uiState.value.copy(state = UiState.State.Data, dashboardRestaurantModels = response.body))
            }
            is NetworkResponse.Error -> {
                val message = response.error.message ?: return@launch
                submitUiState(_uiState.value.copy(state = UiState.State.Error, errorMessage = message))
            }
            else -> Unit
        }
    }


    private fun observeUiEvents() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ListItemClicked -> {
                    if (reloadingDataInProgress) return@collect
                    reloadingDataInProgress = true
                    updateFavorites(event)
                }
                is UiEvent.MenuItemClicked -> {
                    if (reloadingDataInProgress) return@collect
                    reloadingDataInProgress = true
                    reloadData(event.dataSourceType)
                }
            }
        }
    }

    private suspend fun reloadData(dataSourceType : DataSourceType) = viewModelScope.launch {
        when (val response = justEatTakeawayRepository.reloadData(dataSourceType)) {
            is NetworkResponse.Success -> {
                submitUiState(
                    _uiState.value.copy(
                        dashboardRestaurantModels = response.body,
                        dataSourceType = dataSourceType
                    )
                )
                reloadingDataInProgress = false
            }
            is NetworkResponse.Error -> {
                val message = response.error.message ?: return@launch
                submitUiState(_uiState.value.copy(state = UiState.State.Error, errorMessage = message))
                reloadingDataInProgress = false
            }
            else -> Unit
        }
    }

    private suspend fun updateFavorites(event: UiEvent.ListItemClicked) {
        justEatTakeawayRepository.updateFavoriteRestaurant(event.restaurantId, event.isFavorite)
        reloadData(_uiState.value.dataSourceType)
    }

    private fun submitUiState(uiState: UiState) {
        _uiState.update { uiState }
    }

    fun submitEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.emit(uiEvent)
    }

    sealed interface UiEvent {
        data class ListItemClicked(val restaurantId: Int, val isFavorite: Boolean) : UiEvent
        data class MenuItemClicked(val dataSourceType: DataSourceType) : UiEvent
    }

    data class UiState(
        val state: State = State.Initial,
        val dashboardRestaurantModels: List<DashboardRestaurantModel> = emptyList(),
        val dataSourceType: DataSourceType = DataSourceType.NETWORK_DATA,
        var errorMessage: String = ""
    ) {
        enum class State {
            Error,
            Initial,
            Data,
        }
    }
}