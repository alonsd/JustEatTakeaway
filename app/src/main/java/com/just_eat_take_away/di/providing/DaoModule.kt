package com.just_eat_take_away.di.providing

import com.just_eat_take_away.data.source.local.RestaurantDao
import com.just_eat_take_away.database.RestaurantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideRestaurantsDao(restaurantsDatabase : RestaurantsDatabase): RestaurantDao {
        return restaurantsDatabase.getRestaurantsDao()
    }
}