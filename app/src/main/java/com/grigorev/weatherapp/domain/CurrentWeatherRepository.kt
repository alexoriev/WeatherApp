package com.grigorev.weatherapp.domain

interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(): CurrentWeather

}