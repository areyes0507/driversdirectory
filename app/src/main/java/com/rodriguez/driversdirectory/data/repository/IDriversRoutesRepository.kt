package com.rodriguez.driversdirectory.data.repository

import com.rodriguez.driversdirectory.data.ObjResult
import com.rodriguez.driversdirectory.data.datasources.IDriversRoutesDataSource
import com.rodriguez.driversdirectory.data.local.database.DriversDirectoryDatabase
import com.rodriguez.driversdirectory.data.local.database.entities.DriverEntity
import com.rodriguez.driversdirectory.data.local.database.entities.RouteEntity
import com.rodriguez.driversdirectory.data.network.ApiResponse
import com.rodriguez.driversdirectory.data.network.dtos.DriversRoutesResponseDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface IDriversRoutesRepository {
    fun downloadDriversAndRoutes(local: Boolean): Flow<ObjResult<Boolean>>

    suspend fun retrieveLocalDrivers(): List<DriverEntity>

    suspend fun retrieveLocalRoutes(): List<RouteEntity>
}

data class DriversRoutesRepository(
    private val database: DriversDirectoryDatabase,
    private val remoteDataSource: IDriversRoutesDataSource,
    private val localDataSource: IDriversRoutesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : IDriversRoutesRepository {

    override fun downloadDriversAndRoutes(local: Boolean) = flow {
        emit(ObjResult.Loading)
        runCatching {
            when (val response = driverDataSource(local = local).retrieveDriversRoutes()) {
                ApiResponse.ConnectionError -> {
                    emit(ObjResult.ConnectionError)
                }

                is ApiResponse.Fail -> {
                    emit(ObjResult.Error(response.exception))
                }

                is ApiResponse.Success -> {
                    processSuccessResponse(response.data)
                    emit(ObjResult.Success(true))
                }
            }
        }
    }.flowOn(context = dispatcher)

    private fun driverDataSource(local: Boolean): IDriversRoutesDataSource {
        return if (local) localDataSource else remoteDataSource
    }

    override suspend fun retrieveLocalDrivers(): List<DriverEntity> =
        withContext(context = dispatcher) {
            return@withContext database.driversDao().getAllDrivers()
        }

    override suspend fun retrieveLocalRoutes(): List<RouteEntity> = withContext(context = dispatcher
    ) {
        return@withContext database.routesDao().getAllRoutes()
    }

    private suspend fun processSuccessResponse(driversRoutesResponseDto: DriversRoutesResponseDto) {
        val driverEntities = driversRoutesResponseDto.drivers.map {
            DriverEntity(
                id = it.id,
                name = it.name
            )
        }
        database.driversDao().insertDrivers(driverEntities)

        val routesEntities = driversRoutesResponseDto.routes.map {
            RouteEntity(
                id = it.id,
                type = it.type,
                name = it.name
            )
        }
        database.routesDao().insertRoutes(routesEntities)

    }

}