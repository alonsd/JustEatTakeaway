package com.just_eat_take_away.model.server_models


import com.google.gson.annotations.SerializedName

data class RestaurantsResponseModel(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>
) {
    data class Restaurant(
        @SerializedName("coverImageUrl")
        val coverImageUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("minimumOrder")
        val minimumOrder: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("open")
        val `open`: Boolean
    )
}