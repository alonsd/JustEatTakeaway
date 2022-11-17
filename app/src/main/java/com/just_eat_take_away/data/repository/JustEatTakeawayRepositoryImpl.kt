package com.just_eat_take_away.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.just_eat_take_away.data.source.local.JustEatTakeawayLocalDataSource
import com.just_eat_take_away.data.source.remote.JustEatTakeawayRemoteDataSource
import com.just_eat_take_away.model.server_models.RestaurantsResponseModel
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel
import com.just_eat_take_away.model.ui_models.DataSourceType
import javax.inject.Inject

class JustEatTakeawayRepositoryImpl @Inject constructor(
    private val localDataSource: JustEatTakeawayLocalDataSource,
    private val remoteDataSource: JustEatTakeawayRemoteDataSource,
) : JustEatTakeawayRepository {

    override suspend fun getRestaurants(): NetworkResponse<List<DashboardRestaurantModel>, String> {
        val restaurantsResponse = remoteDataSource.getRestaurants()
        if (restaurantsResponse is NetworkResponse.Error)
            return NetworkResponse.ServerError(body = restaurantsResponse.error.localizedMessage, code = 400)
        val dashboardRestaurants = getDashboardRestaurantModels((restaurantsResponse as NetworkResponse.Success).body.restaurants)
        return NetworkResponse.Success(dashboardRestaurants, code = 200)
    }

    override suspend fun updateFavoriteRestaurant(restaurantId: Int, isFavorite: Boolean) {
        localDataSource.updateFavoriteRestaurant(restaurantId, insertToDatabase = isFavorite)
    }

    override suspend fun changeDataSource(dataSourceType: DataSourceType): NetworkResponse<List<DashboardRestaurantModel>, String> {
        if (dataSourceType == DataSourceType.NETWORK_DATA) {
            return getRestaurants()
        }
        val mockedDataRestaurants = localDataSource.getMockedDataRestaurants(dataSourceType)
        val dashboardRestaurantModels = getDashboardRestaurantModels(mockedDataRestaurants.restaurants)
        return NetworkResponse.Success(dashboardRestaurantModels, code = 200)
    }

    private suspend fun getDashboardRestaurantModels(
        restaurants: List<RestaurantsResponseModel.Restaurant>
    ): MutableList<DashboardRestaurantModel> {
        val favoriteRestaurants = localDataSource.getFavoriteRestaurants()
        val dashboardRestaurants = mutableListOf<DashboardRestaurantModel>()
        restaurants.forEach { responseModel ->
            val isFavorite = favoriteRestaurants.find { it.id == responseModel.id } != null
            val model = DashboardRestaurantModel(
                responseModel.id,
                responseModel.coverImageUrl, responseModel.name,
                isFavorite
            )
            dashboardRestaurants.add(model)
        }
        return dashboardRestaurants
    }
}

