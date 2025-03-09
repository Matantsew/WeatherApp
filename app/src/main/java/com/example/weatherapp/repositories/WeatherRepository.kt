package com.example.weatherapp.repositories

import com.example.weatherapp.api.Api
import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast

class WeatherRepository(private val api: Api) {

    suspend fun getWeatherCurrent(
        latitude: Double,
        longitude: Double,
        include: String
    ): WeatherCurrent {
        return api.getWeatherCurrent(latitude, longitude, include)
    }

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): WeatherForecast {
        return api.getWeatherForecast(latitude, longitude)
    }
}