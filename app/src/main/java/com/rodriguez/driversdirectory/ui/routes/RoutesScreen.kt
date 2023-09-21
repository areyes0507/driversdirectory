package com.rodriguez.driversdirectory.ui.routes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodriguez.driversdirectory.domain.model.Route
import com.rodriguez.driversdirectory.ui.DriversDirectoryTopBar
import com.rodriguez.driversdirectory.ui.theme.DriversDirectoryTheme

@Composable
fun RoutesScreen(routesViewModel: RoutesViewModel = hiltViewModel()) {
    val routes = routesViewModel.routesUiState.collectAsState().value
    Surface {
        RoutesScreenContent(routes = routes)
    }
}

@Composable
fun RoutesScreenContent(routes: List<Route>) {
    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize())
    {
        item {
            DriversDirectoryTopBar()
        }
        items(items = routes, key = { driver -> driver.id }) {
            RouteItem(route = it)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview
@Composable
fun DriversPreview() {
    DriversDirectoryTheme {
        Surface {
            RoutesScreenContent(routes =
            listOf(
                Route(
                    id = 0,
                    type = "R",
                    name = "John"
                ),
                Route(
                    id = 2,
                    type = "A",
                    name = "Mike"
                ), Route(
                    id = 3,
                    type = "E",
                    name = "Johnson"
                )
            )
            )
        }
    }
}