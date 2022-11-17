package com.just_eat_take_away.data.source.local

import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity

interface JustEatTakeawayLocalDataSource {

    suspend fun getFavoriteRestaurants() : List<FavoriteRestaurantEntity>

    suspend fun updateFavoriteRestaurant(restaurantId: Int, insertToDatabase :Boolean)
}
