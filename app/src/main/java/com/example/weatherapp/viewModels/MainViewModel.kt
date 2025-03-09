package com.example.weatherapp.viewModels

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.WeatherCurrent
import com.example.weatherapp.models.WeatherForecast
import com.example.weatherapp.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherCurrentFlow = MutableStateFlow<WeatherCurrent?>(null)
    val weatherCurrentFlow = _weatherCurrentFlow.asStateFlow()

    private val _weatherForecastFlow = MutableStateFlow<WeatherForecast?>(null)
    val weatherForecastFlow = _weatherForecastFlow.asStateFlow()

    fun obtainWeather(location: Location) = with(location) {
        viewModelScope.launch {

            repository.getWeatherCurrent(latitude, longitude, "1hour").collect {
                _weatherCurrentFlow.value = it
            }

            repository.getWeatherForecast(latitude, longitude)
        }
    }
}