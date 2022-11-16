package com.just_eat_take_away.data.repository

import android.util.Log
import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.data.source.local.JustEatTakeawayLocalDataSource
import com.just_eat_take_away.data.source.remote.JustEatTakeawayRemoteDataSource
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel
import javax.inject.Inject

class JustEatTakeawayRepositoryImpl @Inject constructor(
    private val localDataSource: JustEatTakeawayLocalDataSource,
    private val remoteDataSource: JustEatTakeawayRemoteDataSource,
) : JustEatTakeawayRepository {

    override suspend fun getRestaurants(): NetworkResponse<List<DashboardRestaurantModel>, String> {
        val restaurantsResponse = remoteDataSource.getRestaurants()
        if (restaurantsResponse is NetworkResponse.Error)
            return NetworkResponse.ServerError(body = restaurantsResponse.error.localizedMessage, code = 400)
        val favoriteRestaurants = localDataSource.getFavoriteRestaurants()
        val dashboardRestaurants = mutableListOf<DashboardRestaurantModel>()
        (restaurantsResponse as NetworkResponse.Success).body.forEach { responseModel ->
            val isFavorite = favoriteRestaurants.find { it.id == responseModel.id } != null
            val model = DashboardRestaurantModel(
                responseModel.id,
                responseModel.coverImageUrl, responseModel.name,
                isFavorite
            )
            dashboardRestaurants.add(model)
        }
        dashboardRestaurants.forEach {
            Log.d("defaultAppDebuger", "restaurant: $it")
        }
        return NetworkResponse.Success(dashboardRestaurants, code = 200)
    }

}

