package com.rodriguez.driversdirectory.data.network

sealed class ApiResponse<out T> {

    class Success<out T>(val data: T) : ApiResponse<T>()

    class Fail(val exception: Exception) : ApiResponse<Nothing>()

    object ConnectionError : ApiResponse<Nothing>()
}