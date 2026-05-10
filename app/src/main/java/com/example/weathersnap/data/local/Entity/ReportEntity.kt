package com.example.weathersnap.data.local.Entity



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cityName: String,
    val temperature: Double,
    val conditionCode: Int,
    val humidity: Double,
    val windSpeed: Double,
    val pressure: Double,
    val notes: String,
    val imagePath: String,
    val originalSizeBytes: Long,
    val compressedSizeBytes: Long,
    val timestamp: Long
)

