package com.example.weathersnap.presentation.screen.ReportsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.usecase.GetReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ReportsState())
    val state = _state.asStateFlow()

    init {

        getReports()

    }

    private fun getReports() {

        viewModelScope.launch {

            getReportsUseCase.getReport().collect { result ->

                when(result) {

                    is ResultState.Loading -> {

                        _state.value = ReportsState(
                            isLoading = true
                        )

                    }

                    is ResultState.Success -> {

                        _state.value = ReportsState(
                            reports = result.data,
                            isLoading = false
                        )

                    }

                    is ResultState.Error -> {

                        _state.value = ReportsState(
                            error = result.message,
                            isLoading = false
                        )

                    }

                }

            }

        }

    }

}

data class ReportsState(

    val isLoading: Boolean = false,

    val reports: List<Report> = emptyList(),

    val error: String? = ""

)