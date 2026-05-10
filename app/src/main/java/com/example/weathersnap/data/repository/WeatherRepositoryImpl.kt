package com.example.weathersnap.data.repository

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.data.mapper.toDomain
import com.example.weathersnap.data.network.apiService.GeocodingApiService
import com.example.weathersnap.data.network.apiService.WeatherApiService
import com.example.weathersnap.domain.models.CitySuggestion
import com.example.weathersnap.domain.models.WeatherInfo
import com.example.weathersnap.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val geocodingApi: GeocodingApiService,
    private val weatherApi: WeatherApiService
) : WeatherRepository {

    private val cache = mutableMapOf<String, List<CitySuggestion>>()

    override fun searchCity(
        query: String
    ): Flow<ResultState<List<CitySuggestion>>> = flow {

        emit(ResultState.Loading)

        try {

            val trimmedQuery = query.trim()

            if (trimmedQuery.length < 3) {

                emit(ResultState.Success(emptyList()))
                return@flow

            }

            cache[trimmedQuery]?.let {

                emit(ResultState.Success(it))
                return@flow

            }

            val response = geocodingApi.searchCities(trimmedQuery)

            val cityList = response.results.map {
                it.toDomain()
            }

            cache[trimmedQuery] = cityList

            emit(ResultState.Success(cityList))

        } catch (e: Exception) {

            emit(
                ResultState.Error(
                    e.message ?: "Failed To Search City"
                )
            )

        }

    }

    override fun getWeather(
        lat: Double,
        lon: Double,
        cityName: String
    ): Flow<ResultState<WeatherInfo>> = flow {

        emit(ResultState.Loading)

        try {

            val response = weatherApi.getWeather(lat, lon)

            val weatherInfo = response.toDomain(cityName)

            if (weatherInfo != null) {

                emit(ResultState.Success(weatherInfo))

            } else {

                emit(ResultState.Error("Weather Data Not Found"))

            }

        } catch (e: Exception) {

            emit(
                ResultState.Error(
                    e.message ?: "Failed To Fetch Weather"
                )
            )

        }

    }

}