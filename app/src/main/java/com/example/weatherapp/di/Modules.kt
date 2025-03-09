package com.example.weatherapp.di

import com.example.weatherapp.api.Api
import com.example.weatherapp.api.ApiBuilder
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.repositories.WeatherRepository
import com.example.weatherapp.viewModels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Api> { ApiBuilder.build() }
    single<WeatherRepository> {
        WeatherRepository(get(),
        WeatherDatabase.getDatabase(androidContext()).weatherCurrentDao()) }
    viewModel<MainViewModel> { MainViewModel(get()) }
}