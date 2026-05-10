package com.example.weathersnap.domain.usecase

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.WeatherInfo
import com.example.weathersnap.domain.repository.WeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase @Inject constructor(private val repo: WeatherRepository) {
      fun getWeather(lat: Double, lon: Double, cityName: String): Flow<ResultState<WeatherInfo>> =
         repo.getWeather(lat, lon, cityName)

}
