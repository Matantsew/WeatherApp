package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.models.WeatherForecastData
import kotlinx.serialization.json.Json

class WeatherForecastListConverter {

    @TypeConverter
    fun fromWeatherForecastDataList(weatherForecastData: List<WeatherForecastData>): String {
        return Json.encodeToString(weatherForecastData)
    }

    @TypeConverter
    fun toWeatherForecastDataList(data: String): List<WeatherForecastData> {
        return Json.decodeFromString(data)
    }
}
