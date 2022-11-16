package com.just_eat_take_away.data.source.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.model.server_models.RestaurantResponseModel

interface JustEatTakeawayRemoteDataSource {

    suspend fun getRestaurants() : NetworkResponse<List<RestaurantResponseModel>, String>
}