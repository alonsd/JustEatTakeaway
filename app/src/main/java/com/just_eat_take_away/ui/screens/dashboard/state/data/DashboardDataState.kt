package com.just_eat_take_away.ui.screens.dashboard.state.data

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel

@Composable
fun DashboardDataState(
    modifier: Modifier = Modifier,
    dashboardRestaurantModels: List<DashboardRestaurantModel>,
    onRestaurantClicked: (restaurantId: Int) -> Unit
) {
    LazyColumn(modifier.fillMaxSize()) {
        items(dashboardRestaurantModels) { model ->
            RestaurantListItem(model = model, onRestaurantClicked = onRestaurantClicked)
        }
    }
}

@Preview
@Composable
fun DashboardDataStatePreview() {
    DashboardDataState(dashboardRestaurantModels = emptyList(),
        onRestaurantClicked = {})
}