package com.just_eat_take_away.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.just_eat_take_away.data.source.dao.RestaurantDao
import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity


@Database(
    entities = [FavoriteRestaurantEntity::class],
    version = 1, exportSchema = false
)
abstract class RestaurantsDatabase : RoomDatabase() {


    abstract fun getRestaurantsDao(): RestaurantDao


}