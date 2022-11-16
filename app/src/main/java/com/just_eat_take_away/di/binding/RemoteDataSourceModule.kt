package com.just_eat_take_away.di.binding

import com.just_eat_take_away.data.source.remote.JustEatTakeawayRemoteDataSource
import com.just_eat_take_away.data.source.remote.JustEatTakeawayRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindJustEatTakeawayRemoteDataSource(
        justEatTakeawayRemoteDataSourceImpl: JustEatTakeawayRemoteDataSourceImpl
    ): JustEatTakeawayRemoteDataSource
}