package com.grigorev.weatherapp.repository

import com.grigorev.weatherapp.api.Api
import com.grigorev.weatherapp.dto.CurrentWeather
import com.grigorev.weatherapp.dto.Forecast

class WeatherRepositoryImpl : WeatherRepository {

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

    override suspend fun getFiveDaysForecast(): List<Forecast> {
        lateinit var forecast: List<Forecast>
        try {
            val response = Api.apiClient.getFiveDaysForecast()
            if (!response.isSuccessful) {
                throw Exception()
            }
            forecast = response.body()?.list ?: throw Exception()
        } catch (e: Exception) {
            throw e
        }
        return forecast
    }
}