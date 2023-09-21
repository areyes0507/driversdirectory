package com.rodriguez.driversdirectory.di

import com.rodriguez.driversdirectory.data.datasources.IDriversRoutesDataSource
import com.rodriguez.driversdirectory.data.local.database.DriversDirectoryDatabase
import com.rodriguez.driversdirectory.data.repository.DriversRoutesRepository
import com.rodriguez.driversdirectory.data.repository.IDriversRoutesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDriversAndRoutesRepository(
        database: DriversDirectoryDatabase,
        @LocalDataSource localDataSource: IDriversRoutesDataSource,
        @RemoteDataSource remoteDataSource: IDriversRoutesDataSource,
    ): IDriversRoutesRepository =
        DriversRoutesRepository(database = database,
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource)
}