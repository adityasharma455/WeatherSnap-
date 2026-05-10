package com.example.weathersnap.domain.usecase

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.CitySuggestion
import com.example.weathersnap.domain.repository.WeatherRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchCityUseCase @Inject constructor(private val repo: WeatherRepository) {
      fun searchCity(query: String): Flow<ResultState<List<CitySuggestion>>> =
         repo.searchCity(query)
    }

