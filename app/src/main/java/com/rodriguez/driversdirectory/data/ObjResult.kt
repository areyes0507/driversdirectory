package com.rodriguez.driversdirectory.data

import java.lang.Exception

sealed class ObjResult<out T> {

    data class Success<out T>(val data: T) : ObjResult<T>()

    data class Error(val exception: Exception) : ObjResult<Nothing>()

    object Loading : ObjResult<Nothing>()

    object ConnectionError : ObjResult<Nothing>()
}