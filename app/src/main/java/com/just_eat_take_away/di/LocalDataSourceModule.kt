package com.just_eat_take_away.di

import com.just_eat_take_away.data.source.local.JustEatTakeawayLocalDataSource
import com.just_eat_take_away.data.source.local.JustEatTakeawayLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindJustEatTakeawayLocalDataSource(
        justEatTakeawayLocalDataSourceImpl: JustEatTakeawayLocalDataSourceImpl
    ): JustEatTakeawayLocalDataSource
}