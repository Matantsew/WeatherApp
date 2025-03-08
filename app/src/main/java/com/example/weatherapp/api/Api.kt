package com.example.weatherapp.api

import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("current")
    suspend fun getWeatherCurrent(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("include") include: String
    ): WeatherCurrent

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): WeatherForecast

}