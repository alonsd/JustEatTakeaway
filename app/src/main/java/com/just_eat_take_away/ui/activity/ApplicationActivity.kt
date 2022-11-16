package com.just_eat_take_away.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.just_eat_take_away.core.extensions.setJustEatTakeawayContent
import com.just_eat_take_away.ui.screens.dashboard.screen.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setJustEatTakeawayContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}