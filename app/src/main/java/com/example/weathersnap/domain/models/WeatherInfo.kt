package com.example.weathersnap.domain.models

data class WeatherInfo(
    val cityName: String,
    val temperature: Double,
    val conditionCode: Int,  // e.g., 0=clear, 1=cloudy, etc.
    val humidity: Double,
    val windSpeed: Double,
    val pressure: Double
)
