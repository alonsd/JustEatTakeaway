package com.just_eat_take_away.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.just_eat_take_away.core.extensions.readAssetsFile
import com.just_eat_take_away.model.database_entities.FavoriteRestaurantEntity
import com.just_eat_take_away.model.server_models.RestaurantsResponseModel
import com.just_eat_take_away.model.ui_models.DataSourceType
import javax.inject.Inject

class JustEatTakeawayLocalDataSourceImpl @Inject constructor(
    private val restaurantDao: RestaurantDao,
    private val context: Context
) : JustEatTakeawayLocalDataSource {

    override suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity> =
        restaurantDao.getFavoriteRestaurants()

    override suspend fun updateFavoriteRestaurant(restaurantId: Int, insertToDatabase: Boolean) {
        if (insertToDatabase) {
            restaurantDao.insertFavoriteRestaurant(FavoriteRestaurantEntity(restaurantId))
            return
        }
        restaurantDao.deleteFavoriteRestaurant(restaurantId)
    }

    override suspend fun getMockedDataRestaurants(dataSourceType: DataSourceType): RestaurantsResponseModel {
        val fileName = when (dataSourceType) {
            DataSourceType.DB_JSON -> {
                "mocked_data/db.json"
            }
            DataSourceType.MOCKED_RESTAURANTS -> {
                "mocked_data/mocked_restaurants.json"
            }
            DataSourceType.NETWORK_DATA -> "" // Case is treated at repository level
        }
        val json = context.assets.readAssetsFile(fileName)
        return Gson().fromJson(json, RestaurantsResponseModel::class.java)!!
    }

}