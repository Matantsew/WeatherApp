package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast

@Database(entities = [WeatherCurrent::class, WeatherForecast::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherCurrentDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "Weather"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}