package com.just_eat_take_away.data.repository

import com.just_eat_take_away.data.source.local.JustEatTakeawayLocalDataSource
import javax.inject.Inject

class JustEatTakeawayRepositoryImpl @Inject constructor(
    private val justEatTakeawayLocalDataSourceImpl: JustEatTakeawayLocalDataSource
) : JustEatTakeawayRepository {

}

