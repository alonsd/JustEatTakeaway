package com.just_eat_take_away.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel
import com.just_eat_take_away.model.ui_models.DataSourceType

interface JustEatTakeawayRepository {

    suspend fun getRestaurants() :NetworkResponse<List<DashboardRestaurantModel>, String>

    suspend fun updateFavoriteRestaurant(restaurantId: Int, isFavorite :Boolean)

    suspend fun changeDataSource(dataSourceType: DataSourceType)
}