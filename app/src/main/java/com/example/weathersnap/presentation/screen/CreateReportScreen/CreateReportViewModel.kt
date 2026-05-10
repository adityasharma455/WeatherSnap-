package com.example.weathersnap.presentation.screen.CreateReportScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.models.WeatherInfo
import com.example.weathersnap.domain.usecase.GetWeatherUseCase
import com.example.weathersnap.domain.usecase.SaveReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CreateReportViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveReportUseCase: SaveReportUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(CreateReportState())
    val state = _state.asStateFlow()

    fun loadWeather(
        lat: Double,
        lon: Double,
        cityName: String
    ) {

        viewModelScope.launch {

            getWeatherUseCase.getWeather(lat, lon, cityName).collect { result ->

                when(result) {

                    is ResultState.Loading -> {

                        _state.update {

                            it.copy(
                                isLoading = true,
                                error = null
                            )

                        }

                    }

                    is ResultState.Success -> {

                        _state.update {

                            it.copy(
                                weatherInfo = result.data,
                                isLoading = false
                            )

                        }

                    }

                    is ResultState.Error -> {

                        _state.update {

                            it.copy(
                                error = result.message,
                                isLoading = false
                            )

                        }

                    }

                }

            }

        }

    }

    fun onPhotoCaptured(uri: Uri) {

        val file = File(uri.path ?: "")

        val originalSize = file.length()

        val bitmap = BitmapFactory.decodeFile(file.absolutePath)

        val compressedFile = File(context.cacheDir, "compressed.jpg")

        FileOutputStream(compressedFile).use {

            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, it)

        }

        val compressedSize = compressedFile.length()

        _state.update {

            it.copy(
                photoUri = uri,
                originalSize = (originalSize / 1024).toInt(),
                compressedSize = (compressedSize / 1024).toInt()
            )

        }

    }

    fun onNotesChanged(notes: String) {

        _state.update {

            it.copy(notes = notes)

        }

    }

    fun saveReport() {

        viewModelScope.launch {

            val weather = _state.value.weatherInfo ?: return@launch

            val uri = _state.value.photoUri ?: return@launch

            val report = Report(

                cityName = weather.cityName,
                temperature = weather.temperature,
                conditionCode = weather.conditionCode,
                humidity = weather.humidity,
                windSpeed = weather.windSpeed,
                pressure = weather.pressure,
                notes = _state.value.notes,
                imagePath = uri.toString(),
                originalSizeBytes = (_state.value.originalSize * 1024).toLong(),
                compressedSizeBytes = (_state.value.compressedSize * 1024).toLong(),
                timestamp = System.currentTimeMillis()

            )

            saveReportUseCase.saveReport(report).collect { result ->

                when(result) {

                    is ResultState.Loading -> {

                        _state.update {

                            it.copy(
                                isLoading = true
                            )

                        }

                    }

                    is ResultState.Success -> {

                        _state.update {

                            it.copy(
                                isLoading = false,
                                success = true
                            )

                        }

                    }

                    is ResultState.Error -> {

                        _state.update {

                            it.copy(
                                isLoading = false,
                                error = result.message
                            )

                        }

                    }

                }

            }

        }

    }

}

data class CreateReportState(

    val isLoading: Boolean = false,

    val success: Boolean = false,

    val weatherInfo: WeatherInfo? = null,

    val photoUri: Uri? = null,

    val notes: String = "",

    val originalSize: Int = 0,

    val compressedSize: Int = 0,

    val error: String? = ""

)