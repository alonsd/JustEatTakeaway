package com.just_eat_take_away.ui.screens.dashboard.state.data

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.just_eat_take_away.R
import com.just_eat_take_away.model.ui_models.DataSourceType

@Composable
fun DashboardTopBar(
    dataSourceType: DataSourceType,
    onMenuItemClicked: (dataSourceType: DataSourceType) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    val networkDataName = R.string.dashboard_screen_top_bar_network_data
    val dbJsonDataName = R.string.dashboard_screen_top_bar_db_json_mocked_data
    val mockedRestaurantsDataName = R.string.dashboard_screen_top_bar_mocked_restaurants_data

    val selectedDataSourceName = when (dataSourceType) {
        DataSourceType.NETWORK_DATA -> networkDataName
        DataSourceType.DB_JSON -> dbJsonDataName
        DataSourceType.MOCKED_RESTAURANTS -> mockedRestaurantsDataName
    }

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
                    onDropdownItemClicked(dataSourceType, DataSourceType.NETWORK_DATA, onMenuItemClicked)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(id = networkDataName))
                }

                DropdownMenuItem(onClick = {
                    onDropdownItemClicked(dataSourceType, DataSourceType.DB_JSON, onMenuItemClicked)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(dbJsonDataName))
                }

                DropdownMenuItem(onClick = {
                    onDropdownItemClicked(dataSourceType, DataSourceType.MOCKED_RESTAURANTS, onMenuItemClicked)
                    menuExpanded = menuExpanded.not()
                }) {
                    Text(text = stringResource(mockedRestaurantsDataName))
                }
            }
        }
    )
}

private fun onDropdownItemClicked(
    currentDataSourceName: DataSourceType,
    selectedDataSourceType: DataSourceType,
    onMenuItemClicked: (dataSourceType: DataSourceType) -> Unit
) {
    if (currentDataSourceName == selectedDataSourceType) return
    onMenuItemClicked(selectedDataSourceType)
}

@Preview(showBackground = true)
@Composable
fun DashboardTopBarPreview() {
    DashboardTopBar(dataSourceType = DataSourceType.NETWORK_DATA) {}
}