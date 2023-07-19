package com.grigorev.weatherapp.repository

import com.grigorev.weatherapp.dto.CurrentWeather
import com.grigorev.weatherapp.dto.Forecast


interface WeatherRepository {

    suspend fun getCurrentWeather(): CurrentWeather

    suspend fun getFiveDaysForecast(): List<Forecast>

}