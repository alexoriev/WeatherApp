package com.grigorev.weatherapp.repository

import com.grigorev.weatherapp.api.Api
import com.grigorev.weatherapp.dto.CurrentWeather

class CurrentWeatherRepositoryImpl: CurrentWeatherRepository {

    override suspend fun getCurrentWeather(): CurrentWeather {
        lateinit var currentWeather: CurrentWeather
        try {
            val response = Api.apiClient.getCurrentWeather()
            if (!response.isSuccessful) {
                throw Exception("${response.code()}: ${response.message()}")
            }
            currentWeather = response.body() ?: throw Exception("${response.code()}: ${response.message()}")
        } catch (e: Exception) {
            throw e
        }
        return currentWeather
    }
}