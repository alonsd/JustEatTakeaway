package com.just_eat_take_away.model.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.just_eat_take_away.core.constants.database_constants.TableConstants

@Entity(tableName = TableConstants.FAVORITE_RESTAURANTS_TABLE)
data class FavoriteRestaurantEntity(
    @PrimaryKey
    var id: Int
)
