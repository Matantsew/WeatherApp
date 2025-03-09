package com.example.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.database.WeatherCurrentListConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "weatherCurrentCache")
@TypeConverters(WeatherCurrentListConverter::class)
data class WeatherCurrent(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val count: Int,
    val data: List<WeatherCurrentData>
)

@Serializable
@Entity(tableName = "weatherCurrentCache")
data class WeatherCurrentData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val city_name: String,
    val temp: Float,
    val sunrise: String,
    val sunset: String,
    val weather: WeatherInfo
)