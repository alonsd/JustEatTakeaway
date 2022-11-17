package com.just_eat_take_away.ui.screens.dashboard.state.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.just_eat_take_away.R
import com.just_eat_take_away.model.ui_models.DashboardRestaurantModel

@ExperimentalMaterialApi
@Composable
fun RestaurantListItem(
    modifier: Modifier = Modifier,
    model: DashboardRestaurantModel,
    onRestaurantClicked: (restaurantId: Int, isFavorite: Boolean) -> Unit
) {
    Card(modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp), onClick = {
        onRestaurantClicked(model.restaurantId, model.isFavorite.not())
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 16.dp, end = 16.dp),
                painter = rememberAsyncImagePainter(
                    model.imageUrl, fallback = painterResource(id = R.drawable.tenbis_logo)
                ),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = model.restaurantName
                )
                IconButton(onClick = {}) {
                    if (model.isFavorite) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun RestaurantListItemPreview() {
    RestaurantListItem(
        model = DashboardRestaurantModel(
            1,
            "https://tenbis-static.azureedge.net/restaurant-default-header-image/CuzineType_sushi.jpg",
            "Sushi Bar",
        ), onRestaurantClicked = { _, _ -> })
}