package com.example.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.database.WeatherForecastListConverter
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "weatherForecastCache")
@TypeConverters(WeatherForecastListConverter::class)
data class WeatherForecast(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val city_name: String,
    val country_code: String,
    val data: List<WeatherForecastData>
)

@Serializable
@Entity(tableName = "weatherForecastCache")
data class WeatherForecastData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val max_temp: Double,
    val min_temp: Double,
    val weather: WeatherInfo
)