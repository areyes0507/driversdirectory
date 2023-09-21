package com.rodriguez.driversdirectory.data.network.dtos

import com.google.gson.annotations.SerializedName

data class BaseResponseDto(
    @SerializedName("error")
    val error: ErrorDto
)

data class ErrorDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("header")
    val header: String,
    @SerializedName("message")
    val message: String
)