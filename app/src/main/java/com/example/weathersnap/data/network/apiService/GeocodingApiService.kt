package com.example.weathersnap.data.network.apiService

import com.example.weathersnap.data.network.dto.GeocodeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("v1/search")
    suspend fun searchCities(
        @Query("name") name: String,
        @Query("count") count: Int = 10,
        @Query("language") language: String = "en"
    ): GeocodeResponseDto
}