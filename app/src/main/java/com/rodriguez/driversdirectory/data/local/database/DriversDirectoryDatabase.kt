package com.rodriguez.driversdirectory.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodriguez.driversdirectory.constants.AppConstants
import com.rodriguez.driversdirectory.data.dao.DriversDao
import com.rodriguez.driversdirectory.data.dao.RoutesDao
import com.rodriguez.driversdirectory.data.local.database.entities.DriverEntity
import com.rodriguez.driversdirectory.data.local.database.entities.RouteEntity

@Database(entities = [DriverEntity::class, RouteEntity::class],
    version = AppConstants.DATA_BASE_VERSION)
abstract class DriversDirectoryDatabase : RoomDatabase() {

    abstract fun driversDao(): DriversDao

    abstract fun routesDao(): RoutesDao

    companion object {
        fun create(context: Context): DriversDirectoryDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = DriversDirectoryDatabase::class.java,
                name = AppConstants.DATA_BASE_NAME
            ).build()
        }
    }
}