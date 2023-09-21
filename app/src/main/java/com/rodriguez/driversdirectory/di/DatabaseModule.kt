package com.rodriguez.driversdirectory.di

import android.content.Context
import com.rodriguez.driversdirectory.data.local.database.DriversDirectoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context) =
        DriversDirectoryDatabase.create(context = context)
}