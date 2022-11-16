package com.just_eat_take_away.di.providing

import android.app.Application
import androidx.room.Room
import com.just_eat_take_away.core.constants.database_constants.DatabaseConstants.RESTAURANTS_DATABASE
import com.just_eat_take_away.database.RestaurantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRestaurantsDatabase(app: Application): RestaurantsDatabase {
        return Room
            .databaseBuilder(app, RestaurantsDatabase::class.java, RESTAURANTS_DATABASE)
            .build()
    }
}