package com.rodriguez.driversdirectory.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteDataSource