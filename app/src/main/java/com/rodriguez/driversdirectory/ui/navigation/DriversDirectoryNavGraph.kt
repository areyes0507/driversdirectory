package com.rodriguez.driversdirectory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodriguez.driversdirectory.ui.drivers.DriversScreen
import com.rodriguez.driversdirectory.ui.drivers.DriversViewModel
import com.rodriguez.driversdirectory.ui.routes.RoutesScreen

@Composable
fun DriversDirectoryNavGraph(navHostController: NavHostController,
                             driversViewModel: DriversViewModel = hiltViewModel()) {
    NavHost(
        navController = navHostController, startDestination = DriversDestination.route) {
        composable(route = DriversDestination.route) {
            DriversScreen(onDriverClick = {
                navHostController.navigate(route = "${RoutesDestination.route}/${it.id}")
            }, driversViewModel = driversViewModel)
        }
        composable(route = RoutesDestination.routeWithArg,
            arguments = RoutesDestination.arguments) {
            RoutesScreen()
        }
    }
}