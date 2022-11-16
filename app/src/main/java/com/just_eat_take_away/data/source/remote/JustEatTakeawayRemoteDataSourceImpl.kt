package com.just_eat_take_away.data.source.remote

import javax.inject.Inject

class JustEatTakeawayRemoteDataSourceImpl @Inject constructor(
    private val restaurantsApi: RestaurantsApi
) : JustEatTakeawayRemoteDataSource {

    override suspend fun getRestaurants() = restaurantsApi.getRestaurants()


}