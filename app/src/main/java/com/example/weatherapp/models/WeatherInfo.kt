package com.example.weatherapp.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val description: String
)
