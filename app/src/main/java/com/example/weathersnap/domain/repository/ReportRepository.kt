package com.example.weathersnap.domain.repository

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.domain.models.Report
import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun saveReport(report: Report): Flow<ResultState<Boolean>>

    fun getReports(): Flow<ResultState<List<Report>>>

}