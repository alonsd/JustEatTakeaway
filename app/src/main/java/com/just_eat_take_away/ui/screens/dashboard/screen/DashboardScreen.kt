package com.just_eat_take_away.ui.screens.dashboard.screen

import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.just_eat_take_away.core.ui.GenericLoadingState
import com.just_eat_take_away.ui.screens.dashboard.state.data.DashboardDataState
import com.just_eat_take_away.ui.screens.dashboard.viewmodel.DashboardViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@ExperimentalComposeUiApi
@ExperimentalGetImage
@ExperimentalMaterialApi
@Destination
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    when (uiState.state) {
        DashboardViewModel.UiState.State.Error -> {
            Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
        DashboardViewModel.UiState.State.Initial -> {
            GenericLoadingState()
        }
        DashboardViewModel.UiState.State.Data -> {
            DashboardDataState(dashboardRestaurantModels = uiState.dashboardRestaurantModels) { restaurantId, isFavorite ->
                viewModel.submitEvent(DashboardViewModel.UiEvent.ListItemClicked(restaurantId, isFavorite))
            }
        }
    }
}

