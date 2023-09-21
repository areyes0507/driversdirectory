package com.rodriguez.driversdirectory.di

import com.rodriguez.driversdirectory.data.datasources.DriverRoutersDataSource
import com.rodriguez.driversdirectory.data.datasources.DriverRoutersLocalDataSource
import com.rodriguez.driversdirectory.data.datasources.IDriversRoutesDataSource
import com.rodriguez.driversdirectory.data.network.DriversRoutesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @RemoteDataSource
    @Singleton
    @Provides
    fun providesRemoteDataSource(service: DriversRoutesService): IDriversRoutesDataSource =
        DriverRoutersDataSource(rsService = service)

    @LocalDataSource
    @Singleton
    @Provides
    fun providesLocalDataSource(): IDriversRoutesDataSource =
        DriverRoutersLocalDataSource()

}