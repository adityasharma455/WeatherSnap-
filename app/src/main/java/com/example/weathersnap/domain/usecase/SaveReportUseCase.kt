package com.example.weathersnap.domain.usecase

import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.repository.ReportRepository
import jakarta.inject.Inject

class SaveReportUseCase @Inject constructor(private val repo: ReportRepository) {
        fun saveReport(report: Report) =
        repo.saveReport(report)

}
