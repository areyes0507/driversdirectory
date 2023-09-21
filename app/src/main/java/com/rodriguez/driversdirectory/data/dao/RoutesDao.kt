package com.rodriguez.driversdirectory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodriguez.driversdirectory.data.local.database.entities.RouteEntity

@Dao
interface RoutesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(routes: List<RouteEntity>)

    @Query("SELECT * FROM routes")
    suspend fun getAllRoutes(): List<RouteEntity>
}