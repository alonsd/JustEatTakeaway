package com.just_eat_take_away.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity


@Dao
interface RestaurantDao {

    @Query("select * from FavoriteRestaurants")
    suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity>

    @Insert
    suspend fun insertFavoriteRestaurant(entity: FavoriteRestaurantEntity)

    @Query("delete from FavoriteRestaurants where id = :restaurantId")
    suspend fun deleteFavoriteRestaurant(restaurantId: Int)

}