package com.grigorev.weatherapp.domain

class GetCurrentWeatherUseCase(private val currentWeatherRepository: CurrentWeatherRepository) {

    suspend fun getCurrentWeather(): CurrentWeather {
        return currentWeatherRepository.getCurrentWeather()
    }
}