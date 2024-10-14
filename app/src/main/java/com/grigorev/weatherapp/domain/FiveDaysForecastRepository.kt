package com.grigorev.weatherapp.domain

interface FiveDaysForecastRepository {

    suspend fun getFiveDaysForecast(): List<Forecast>

}