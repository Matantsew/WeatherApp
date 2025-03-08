package com.example.weatherapp.viewModels

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.ApiBuilder
import com.example.weatherapp.models.WeatherCurrent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _weatherCurrentFlow = MutableStateFlow<WeatherCurrent?>(null)
    val weatherCurrentFlow = _weatherCurrentFlow.asStateFlow()

    private val api = ApiBuilder.build()

    fun obtainWeatherForecast(location: Location) = with(location) {
        viewModelScope.launch {
            val response = api.getWeatherCurrent(latitude, longitude, "1hour")
            _weatherCurrentFlow.value = response
        }
    }

}