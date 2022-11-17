package com.just_eat_take_away.data.source.local

import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity
import com.just_eat_take_away.model.server_models.RestaurantsResponseModel
import com.just_eat_take_away.model.ui_models.DataSourceType

interface JustEatTakeawayLocalDataSource {

    suspend fun getFavoriteRestaurants() : List<FavoriteRestaurantEntity>

    suspend fun updateFavoriteRestaurant(restaurantId: Int, insertToDatabase :Boolean)

    suspend fun getMockedDataRestaurants(dataSourceType: DataSourceType): RestaurantsResponseModel?

}
