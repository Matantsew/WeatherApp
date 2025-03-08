package com.example.weatherapp.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherCurrent(
    val count: Int,
    val data: List<WeatherCurrentData>
)

@Serializable
data class WeatherCurrentData(
    val city_name: String,
    val temp: Float,
    val sunrise: String,
    val sunset: String
)