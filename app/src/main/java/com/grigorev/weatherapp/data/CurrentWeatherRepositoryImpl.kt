package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.CurrentWeatherRepository
import javax.inject.Inject

class CurrentWeatherRepositoryImpl @Inject constructor(
    private val apiService: Api,
    private val mapper: CurrentWeatherMapper
) : CurrentWeatherRepository {

    override suspend fun getCurrentWeather(): CurrentWeather {
        val currentWeather: CurrentWeather
        try {
            val response = apiService.getCurrentWeather()
            if (!response.isSuccessful) {
                throw Exception()
            }
            currentWeather = mapper.mapCurrentWeatherFromDto(response.body()) ?: throw Exception()
        } catch (e: Exception) {
            throw e
        }
        return currentWeather
    }
}