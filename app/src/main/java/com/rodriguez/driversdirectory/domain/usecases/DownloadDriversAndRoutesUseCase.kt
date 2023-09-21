package com.rodriguez.driversdirectory.domain.usecases

import com.rodriguez.driversdirectory.data.ObjResult
import com.rodriguez.driversdirectory.data.repository.IDriversRoutesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadDriversAndRoutesUseCase @Inject constructor(
    private val driversRoutesRepository: IDriversRoutesRepository
) {
    operator fun invoke(): Flow<ObjResult<Boolean>> {
        return driversRoutesRepository.downloadDriversAndRoutes(local = false)
    }
}