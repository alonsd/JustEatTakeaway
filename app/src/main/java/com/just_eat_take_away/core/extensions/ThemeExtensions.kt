package com.just_eat_take_away.core.extensions

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.just_eat_take_away.ui.theme.JustEatTakeawayTheme

fun ComponentActivity.setJustEatTakeawayContent(content: @Composable () -> Unit){
    setContent {
        JustEatTakeawayTheme {
            content()
        }
    }
}