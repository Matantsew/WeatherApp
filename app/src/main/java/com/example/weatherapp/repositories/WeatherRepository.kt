package com.example.weatherapp.repositories

import com.example.weatherapp.api.Api
import com.example.weatherapp.database.WeatherDao
import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class WeatherRepository(private val api: Api,
                        private val dao: WeatherDao
) {

    suspend fun getWeatherCurrent(
        latitude: Double,
        longitude: Double,
        include: String
    ): Flow<WeatherCurrent> = flow {

        val cached = dao.getWeatherCurrent().first()
        emit(cached)

        try {
            val fetched = api.getWeatherCurrent(latitude, longitude, include)
            dao.insertWeatherCurrentAll(fetched)
            emit(fetched)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ):  Flow<WeatherForecast> = flow {

        val cached = dao.getWeatherForecast().first()
        emit(cached)

        try {
            val fetched = api.getWeatherForecast(latitude, longitude)
            dao.insertWeatherForecastAll(fetched)
            emit(fetched)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}