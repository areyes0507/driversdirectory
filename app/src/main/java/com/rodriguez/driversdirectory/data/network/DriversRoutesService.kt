package com.rodriguez.driversdirectory.data.network

import com.rodriguez.driversdirectory.data.network.dtos.DriversRoutesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface DriversRoutesService {

    @GET("/data")
    suspend fun retrieveDriversAndRoutes(): Response<DriversRoutesResponseDto>

}