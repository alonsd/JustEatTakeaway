package com.just_eat_take_away.model.ui_models

data class DashboardRestaurantModel(
    val restaurantId : Int,
    val imageUrl : String,
    val restaurantName : String,
    var isFavorite : Boolean = false
)
