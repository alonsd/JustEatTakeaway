package com.just_eat_take_away.di.binding

import com.just_eat_take_away.data.repository.JustEatTakeawayRepository
import com.just_eat_take_away.data.repository.JustEatTakeawayRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindJustEatTakeawayRepository(
        justEatTakeawayRepositoryImpl: JustEatTakeawayRepositoryImpl
    ): JustEatTakeawayRepository

}