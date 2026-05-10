package com.example.weathersnap.data.network.apiService

import com.example.weathersnap.data.network.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("hourly") hourly: String = "relative_humidity_2m,pressure_msl,windspeed_10m",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponseDto
}