package com.example.weathersnap.data.network.dto


import com.google.gson.annotations.SerializedName

data class GeocodeResponseDto(
    val results: List<CitySuggestionDto> = emptyList()
)
data class CitySuggestionDto(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("country") val country: String? = null
)
data class WeatherResponseDto(
    @SerializedName("current_weather") val current: CurrentWeatherDto? = null,
    val hourly: HourlyDto? = null
)
data class CurrentWeatherDto(
    val temperature: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("weathercode") val weatherCode: Int
)
data class HourlyDto(
    @SerializedName("relative_humidity_2m") val humidity: List<Double> = emptyList(),
    @SerializedName("pressure_msl") val pressure: List<Double> = emptyList()
)
