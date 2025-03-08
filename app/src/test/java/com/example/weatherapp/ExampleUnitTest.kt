package com.example.weatherapp

import com.example.weatherapp.api.ApiBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {
        private val api by lazy { ApiBuilder.build() }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun apiTest() {
        runBlocking {
            val response = api.getWeatherForecast(52.1990483,21.0180319)
            println(response)
        }
    }
}