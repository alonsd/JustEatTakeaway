package com.just_eat_take_away.core.extensions

import android.content.res.AssetManager

fun AssetManager.readAssetsFile(fileName: String): String = open(fileName).bufferedReader().use { it.readText() }
