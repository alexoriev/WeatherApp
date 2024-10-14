package com.grigorev.weatherapp.domain

import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository
) {

    suspend fun getCurrentWeather(): CurrentWeather {
        return currentWeatherRepository.getCurrentWeather()
    }
}