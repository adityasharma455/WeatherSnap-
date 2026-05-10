package com.example.weathersnap.data.mapper


import com.example.weathersnap.data.local.Entity.ReportEntity
import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.models.CitySuggestion
import com.example.weathersnap.domain.models.WeatherInfo
import com.example.weathersnap.data.network.dto.CitySuggestionDto
import com.example.weathersnap.data.network.dto.WeatherResponseDto

fun CitySuggestionDto.toDomain(): CitySuggestion {
    // Include country if present
    val fullName = name + (country?.let { ", $it" } ?: "")
    return CitySuggestion(fullName, latitude, longitude)
}

fun WeatherResponseDto.toDomain(cityName: String): WeatherInfo? {
    val current = current ?: return null
    val humidity = hourly?.humidity?.firstOrNull() ?: 0.0
    val pressure = hourly?.pressure?.firstOrNull() ?: 0.0
    return WeatherInfo(cityName, current.temperature, current.weatherCode, humidity, current.windSpeed, pressure)
}

// Convert Room entity to domain and back
fun ReportEntity.toDomain(): Report {
    return Report(
        id = id,
        cityName = cityName,
        temperature = temperature,
        conditionCode = conditionCode,
        humidity = humidity,
        windSpeed = windSpeed,
        pressure = pressure,
        notes = notes,
        imagePath = imagePath,
        originalSizeBytes = originalSizeBytes,
        compressedSizeBytes = compressedSizeBytes,
        timestamp = timestamp
    )
}
fun Report.toEntity(): ReportEntity {
    return ReportEntity(
        id = id,
        cityName = cityName,
        temperature = temperature,
        conditionCode = conditionCode,
        humidity = humidity,
        windSpeed = windSpeed,
        pressure = pressure,
        notes = notes,
        imagePath = imagePath,
        originalSizeBytes = originalSizeBytes,
        compressedSizeBytes = compressedSizeBytes,
        timestamp = timestamp
    )
}
