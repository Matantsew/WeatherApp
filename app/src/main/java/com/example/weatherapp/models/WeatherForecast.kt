package com.example.weatherapp.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val city_name: String,
    val country_code: String,
    val data: List<WeatherForecastData>
)

@Serializable
data class WeatherForecastData(
    val max_temp: Double,
    val min_temp: Double,
    val weather: WeatherInfo
)