package com.just_eat_take_away.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity


@Dao
interface RestaurantDao {

    @Query("select * from FavoriteRestaurants")
    suspend fun getFavoriteRestaurants() : List<FavoriteRestaurantEntity>

}