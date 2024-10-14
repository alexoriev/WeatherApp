package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.domain.FiveDaysForecastRepository
import com.grigorev.weatherapp.domain.Forecast

class FiveDaysForecastRepositoryImpl : FiveDaysForecastRepository {

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