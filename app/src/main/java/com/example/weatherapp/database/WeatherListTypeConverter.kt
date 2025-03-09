package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.models.WeatherCurrentData
import kotlinx.serialization.json.Json

class WeatherListTypeConverter {

    @TypeConverter
    fun fromWeatherCurrentDataList(weatherCurrentData: List<WeatherCurrentData>): String {
        return Json.encodeToString(weatherCurrentData)
    }

    @TypeConverter
    fun toWeatherCurrentDataList(data: String): List<WeatherCurrentData> {
        return Json.decodeFromString(data)
    }
}
