package com.rodriguez.driversdirectory.data.network.dtos

import com.google.gson.annotations.SerializedName

data class DriversRoutesResponseDto(
    @SerializedName("drivers")
    val drivers: List<DriverDto>,
    @SerializedName("routes")
    val routes: List<RouteDto>
)

data class DriverDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class RouteDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String
)