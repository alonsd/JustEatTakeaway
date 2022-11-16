package com.just_eat_take_away.data.source.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.model.server_models.RestaurantResponseModel
import retrofit2.http.GET


interface RestaurantsApi {

    @GET("10bis/10bis-mobile-home-assignment/db")
    suspend fun getRestaurants() : NetworkResponse<List<RestaurantResponseModel>, String>
}