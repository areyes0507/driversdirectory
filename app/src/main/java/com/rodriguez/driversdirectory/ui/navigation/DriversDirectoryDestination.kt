package com.rodriguez.driversdirectory.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface DriversDirectoryDestination {
    val route: String
}

object DriversDestination : DriversDirectoryDestination {
    override val route: String
        get() = "drivers-destination"
}

object RoutesDestination : DriversDirectoryDestination {
    override val route: String
        get() = "route-details"
    const val driverIdentifierArgument = "driver_identifier"
    val routeWithArg = "$route/{$driverIdentifierArgument}"
    val arguments = listOf(
        navArgument(
            name = driverIdentifierArgument,
            builder = { type = NavType.IntType }
        )
    )
}