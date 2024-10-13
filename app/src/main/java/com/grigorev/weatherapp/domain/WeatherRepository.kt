package com.grigorev.weatherapp.domain

interface WeatherRepository {

    suspend fun getCurrentWeather(): CurrentWeather

    suspend fun getFiveDaysForecast(): List<Forecast>

}