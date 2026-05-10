package com.example.weathersnap.presentation.screen.WeatherScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.CitySuggestion
import com.example.weathersnap.domain.models.WeatherInfo
import com.example.weathersnap.domain.usecase.GetWeatherUseCase
import com.example.weathersnap.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state = _state.asStateFlow()

    fun onQueryChanged(query: String) {

        _state.value = _state.value.copy(
            query = query
        )

        if (query.length < 3) {

            _state.value = _state.value.copy(
                suggestions = emptyList()
            )

            return

        }

        viewModelScope.launch {

            searchCityUseCase.searchCity(query).collect { result ->

                when (result) {

                    is ResultState.Loading -> {

                        _state.value = _state.value.copy(
                            loading = true,
                            error = null
                        )

                    }

                    is ResultState.Success -> {

                        _state.value = _state.value.copy(
                            loading = false,
                            suggestions = result.data
                        )

                    }

                    is ResultState.Error -> {

                        _state.value = _state.value.copy(
                            loading = false,
                            error = result.message
                        )

                    }

                }

            }

        }

    }

    fun onCitySelected(city: CitySuggestion) {

        viewModelScope.launch {

            getWeatherUseCase.getWeather(
                city.latitude,
                city.longitude,
                city.name
            ).collect { result ->

                when (result) {

                    is ResultState.Loading -> {

                        _state.value = _state.value.copy(
                            loading = true,
                            error = null
                        )

                    }

                    is ResultState.Success -> {

                        _state.value = _state.value.copy(
                            loading = false,
                            weather = result.data,
                            weatherLat = city.latitude,
                            weatherLon = city.longitude,
                            suggestions = emptyList(),
                            query = city.name
                        )

                    }

                    is ResultState.Error -> {

                        _state.value = _state.value.copy(
                            loading = false,
                            error = result.message
                        )

                    }

                }

            }

        }

    }

}

data class WeatherUiState(

    val query: String = "",

    val suggestions: List<CitySuggestion> = emptyList(),

    val loading: Boolean = false,

    val weather: WeatherInfo? = null,

    val weatherLat: Double = 0.0,

    val weatherLon: Double = 0.0,

    val error: String? = null

) {

    val helperVisible: Boolean
        get() = query.length < 3

}