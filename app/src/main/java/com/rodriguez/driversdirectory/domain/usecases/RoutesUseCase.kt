package com.rodriguez.driversdirectory.domain.usecases

import com.rodriguez.driversdirectory.data.repository.IDriversRoutesRepository
import com.rodriguez.driversdirectory.domain.model.Route
import javax.inject.Inject

class RoutesUseCase @Inject constructor(
    private val driversRoutesRepository: IDriversRoutesRepository
) {
    suspend operator fun invoke(driverIdentifier: Int): List<Route> {
        val routeList = driversRoutesRepository.retrieveLocalRoutes().map {
            Route(
                id = it.id,
                type = it.type,
                name = it.name
            )
        }

        val routeListFilteredByIdentifier = routeList.filter { driverIdentifier == it.id }
        if (routeListFilteredByIdentifier.isNotEmpty()) {
            return routeListFilteredByIdentifier
        }
        val filteredRoute = when {
            (driverIdentifier % 2 == 0) -> routeList.firstOrNull { it.type == "R" }
            (driverIdentifier % 5 == 0) -> routeList.firstOrNull { it.type == "C" }
            else -> {
                routeList.lastOrNull { it.type == "I" }
            }
        }
        return filteredRoute?.let {
            return listOf(it)
        } ?: emptyList()
    }
}