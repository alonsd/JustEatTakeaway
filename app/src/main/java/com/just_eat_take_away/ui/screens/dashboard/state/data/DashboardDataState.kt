package com.just_eat_take_away.ui.screens.dashboard.state.data

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.just_eat_take_away.R
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel

@ExperimentalMaterialApi
@Composable
fun DashboardDataState(
    modifier: Modifier = Modifier,
    dashboardRestaurantModels: List<DashboardRestaurantModel>,
    @StringRes selectedDataSourceName: Int,
    onRestaurantClicked: (restaurantId: Int, isFavorite: Boolean) -> Unit,
    onMenuItemClicked: (selectedDataSourceName: Int) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            DashboardTopBar(selectedDataSourceName, onMenuItemClicked)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(dashboardRestaurantModels) { model ->
                RestaurantListItem(model = model, onRestaurantClicked = onRestaurantClicked)
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DashboardDataStatePreview() {
    DashboardDataState(
        dashboardRestaurantModels = emptyList(),
        selectedDataSourceName = R.string.dashboard_screen_top_bar_network_data,
        onRestaurantClicked = { _, _ -> }, onMenuItemClicked = {})
}