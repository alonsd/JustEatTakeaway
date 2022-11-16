package com.just_eat_take_away.data.source.local

import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity
import javax.inject.Inject

class JustEatTakeawayLocalDataSourceImpl @Inject constructor(
    private val restaurantDao: RestaurantDao
) : JustEatTakeawayLocalDataSource {

    override suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity> =
        restaurantDao.getFavoriteRestaurants()


}