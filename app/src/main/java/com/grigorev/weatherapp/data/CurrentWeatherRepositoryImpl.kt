package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.CurrentWeatherRepository

class CurrentWeatherRepositoryImpl : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(): CurrentWeather {
        lateinit var currentWeather: CurrentWeather
        try {
            val response = Api.apiClient.getCurrentWeather()
            if (!response.isSuccessful) {
                throw Exception()
            }
            currentWeather = response.body() ?: throw Exception()
        } catch (e: Exception) {
            throw e
        }
        return currentWeather
    }
}