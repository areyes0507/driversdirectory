package com.rodriguez.driversdirectory.di

import com.rodriguez.driversdirectory.data.network.DriversRoutesService
import com.rodriguez.driversdirectory.enums.EnvType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): DriversRoutesService =
        Retrofit.Builder().baseUrl(EnvType.TEST.url).addConverterFactory(
            GsonConverterFactory.create()).build().create(DriversRoutesService::class.java)

}