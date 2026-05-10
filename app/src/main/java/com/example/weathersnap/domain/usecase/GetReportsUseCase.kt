package com.example.weathersnap.domain.usecase

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.repository.ReportRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetReportsUseCase @Inject constructor(private val repo: ReportRepository) {
     fun getReport(): Flow<ResultState<List<Report>>> = repo.getReports()
}
