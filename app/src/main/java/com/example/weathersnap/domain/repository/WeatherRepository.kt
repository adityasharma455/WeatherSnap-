package com.example.weathersnap.domain.repository



import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.CitySuggestion
import com.example.weathersnap.domain.models.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
     fun searchCity(query: String): Flow<ResultState<List<CitySuggestion>>>
     fun getWeather(lat: Double, lon: Double,   cityName: String): Flow<ResultState<WeatherInfo>>
}
