package com.grigorev.weatherapp.repository

import com.grigorev.weatherapp.dto.CurrentWeather


interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(): CurrentWeather

}