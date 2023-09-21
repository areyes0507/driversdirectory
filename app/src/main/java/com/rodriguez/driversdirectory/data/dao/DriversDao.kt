package com.rodriguez.driversdirectory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodriguez.driversdirectory.data.local.database.entities.DriverEntity

@Dao
interface DriversDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrivers(drivers: List<DriverEntity>)

    @Query("SELECT * FROM drivers")
    suspend fun getAllDrivers(): List<DriverEntity>
}