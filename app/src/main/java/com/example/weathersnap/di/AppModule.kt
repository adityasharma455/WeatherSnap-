package com.example.weathersnap.di

import android.content.Context
import androidx.room.Room
import com.example.weathersnap.data.local.WeatherSnapDatabase
import com.example.weathersnap.data.local.dao.ReportDao
import com.example.weathersnap.data.network.apiService.GeocodingApiService
import com.example.weathersnap.data.network.apiBuilder.WeatherApiBuilder
import com.example.weathersnap.data.network.apiService.WeatherApiService
import com.example.weathersnap.data.repository.ReportRepositoryImpl
import com.example.weathersnap.data.repository.WeatherRepositoryImpl
import com.example.weathersnap.domain.repository.ReportRepository
import com.example.weathersnap.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides @Singleton
    fun provideGeocodingService(): GeocodingApiService {
        return WeatherApiBuilder.geocodingApi
    }
    @Provides @Singleton
    fun provideWeatherService(): WeatherApiService {
        return WeatherApiBuilder.weatherApi
    }

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext app: Context): WeatherSnapDatabase {
        return Room.databaseBuilder(app, WeatherSnapDatabase::class.java, "weather_snap_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides @Singleton
    fun provideReportDao(db: WeatherSnapDatabase): ReportDao = db.reportDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds @Singleton
    abstract fun bindReportRepository(impl: ReportRepositoryImpl): ReportRepository
}
