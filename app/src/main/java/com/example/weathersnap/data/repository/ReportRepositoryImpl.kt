package com.example.weathersnap.data.repository

import com.example.weathersnap.common.ResultState
import com.example.weathersnap.data.local.dao.ReportDao
import com.example.weathersnap.data.mapper.toDomain
import com.example.weathersnap.data.mapper.toEntity
import com.example.weathersnap.domain.models.Report
import com.example.weathersnap.domain.repository.ReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val dao: ReportDao
) : ReportRepository {

    override fun saveReport(report: Report): Flow<ResultState<Boolean>> = flow {

        emit(ResultState.Loading)

        try {

            dao.insert(report.toEntity())

            emit(ResultState.Success(true))

        } catch (e: Exception) {

            emit(ResultState.Error(e.message ?: "Failed To Save Report"))

        }

    }

    override fun getReports(): Flow<ResultState<List<Report>>> {

        return dao.getAllReports().map { entityList ->

            try {

                val reports = entityList.map {
                    it.toDomain()
                }

                ResultState.Success(reports)

            } catch (e: Exception) {

                ResultState.Error(e.message ?: "Failed To Load Reports")

            }

        }

    }

}