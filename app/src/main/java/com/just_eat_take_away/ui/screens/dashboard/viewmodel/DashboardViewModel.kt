package com.just_eat_take_away.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.just_eat_take_away.data.repository.JustEatTakeawayRepository
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

    init {
        observeUiEvents()
    }


    private fun observeUiEvents() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                UiEvent.DeviceTiltSubmitButtonClicked -> {}
                UiEvent.OnFaceRecognized -> {}
                UiEvent.StartButtonClicked -> {}
            }
        }
    }

    private fun submitUiState(uiState: UiState) {
        _uiState.update { uiState }
    }

    fun submitEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.emit(uiEvent)
    }

    sealed interface UiEvent {
        object StartButtonClicked : UiEvent
        object DeviceTiltSubmitButtonClicked : UiEvent
        object OnFaceRecognized : UiEvent
    }

    data class UiState(
        val state: State = State.Initial,
        var errorMessage : String = ""
    ) {
        enum class State {
            Error,
            Initial,
        }
    }
}