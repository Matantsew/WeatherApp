package com.example.weatherapp.api

import com.example.weatherapp.API_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object ApiBuilder {

    fun build(): Api {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(ApiKeyInterceptor())

        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(clientBuilder.build())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(Api::class.java)
    }
}