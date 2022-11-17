package com.just_eat_take_away.ui.screens.dashboard.state.data

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.just_eat_take_away.R

@Composable
fun DashboardTopBar(
    @StringRes selectedDataSourceName: Int,
    onMenuItemClicked: (selectedDataSourceName: Int) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(stringResource(selectedDataSourceName), color = MaterialTheme.colors.onSecondary)
        }, backgroundColor = MaterialTheme.colors.secondary,
        actions = {
            IconButton(onClick = { menuExpanded = menuExpanded.not() }) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {

                DropdownMenuItem(onClick = {
                    onMenuItemClicked(R.string.dashboard_screen_top_bar_network_data)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(id = R.string.dashboard_screen_top_bar_network_data))
                }

                DropdownMenuItem(onClick = {
                    onMenuItemClicked(R.string.dashboard_screen_top_bar_db_json_mocked_data)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(R.string.dashboard_screen_top_bar_db_json_mocked_data))
                }

                DropdownMenuItem(onClick = {
                    onMenuItemClicked(R.string.dashboard_screen_top_bar_mocked_restaurants_data)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(R.string.dashboard_screen_top_bar_mocked_restaurants_data))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardTopBarPreview() {
    DashboardTopBar(R.string.dashboard_screen_top_bar_network_data) {}
}