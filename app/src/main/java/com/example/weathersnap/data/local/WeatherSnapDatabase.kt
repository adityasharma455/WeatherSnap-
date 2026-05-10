package com.example.weathersnap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathersnap.data.local.Entity.ReportEntity
import com.example.weathersnap.data.local.dao.ReportDao

@Database(entities = [ReportEntity::class], version = 1, exportSchema = false)
abstract class WeatherSnapDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}
