package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherCurrentCache")
    fun getWeatherCurrent(): Flow<WeatherCurrent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherCurrentAll(weatherCurrent: WeatherCurrent)

    @Query("SELECT * FROM weatherForecastCache")
    fun getWeatherForecast(): Flow<WeatherForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecastAll(weatherForecast: WeatherForecast)
}