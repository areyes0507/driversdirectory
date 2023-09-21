package com.rodriguez.driversdirectory.di

import com.rodriguez.driversdirectory.data.repository.IDriversRoutesRepository
import com.rodriguez.driversdirectory.domain.usecases.DriversUseCase
import com.rodriguez.driversdirectory.domain.usecases.RoutesUseCase
import com.rodriguez.driversdirectory.domain.usecases.SortDriversUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesDriversUseCase(driversRoutesRepository: IDriversRoutesRepository) = DriversUseCase(
        driversRoutesRepository = driversRoutesRepository
    )

    @Provides
    fun providesRoutesUseCase(driversRoutesRepository: IDriversRoutesRepository) = RoutesUseCase(
        driversRoutesRepository = driversRoutesRepository
    )

    @Provides
    fun providesSortDriversUseCase(driversRoutesRepository: IDriversRoutesRepository) = SortDriversUseCase(
        driversRoutesRepository = driversRoutesRepository
    )
}