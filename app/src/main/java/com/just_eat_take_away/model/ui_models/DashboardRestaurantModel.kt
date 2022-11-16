package com.just_eat_take_away.model.ui_models

data class DashboardRestaurantModel(
    val id : Int,
    val imageUrl : String,
    val restaurantName : String,
    val isFavorite : Boolean = false
)
