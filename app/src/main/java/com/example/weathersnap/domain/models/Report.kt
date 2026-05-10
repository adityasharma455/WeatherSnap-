package com.example.weathersnap.domain.models

data class Report(
    val id: Long = 0,
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
