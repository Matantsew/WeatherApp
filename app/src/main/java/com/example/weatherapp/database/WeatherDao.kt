package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.models.WeatherCurrent
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherCache")
    fun getCurrentWeather(): Flow<WeatherCurrent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherCurrent: WeatherCurrent)
}