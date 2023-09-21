package com.rodriguez.driversdirectory.domain.usecases

import com.rodriguez.driversdirectory.data.repository.IDriversRoutesRepository
import com.rodriguez.driversdirectory.domain.model.Driver
import javax.inject.Inject

class DriversUseCase @Inject constructor(
    private val driversRoutesRepository: IDriversRoutesRepository
) {
    suspend operator fun invoke(): List<Driver> {
        return driversRoutesRepository.retrieveLocalDrivers().map {
            Driver(
                id = it.id,
                name = it.name
            )
        }
    }
}