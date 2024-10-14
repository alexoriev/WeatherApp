package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.domain.FiveDaysForecastRepository
import com.grigorev.weatherapp.domain.Forecast

class FiveDaysForecastRepositoryImpl : FiveDaysForecastRepository {

    private val mapper = FiveDaysForecastMapper()

    override suspend fun getFiveDaysForecast(): List<Forecast> {
        lateinit var forecast: List<Forecast>
        try {
            val response = Api.apiClient.getFiveDaysForecast()
            if (!response.isSuccessful) {
                throw Exception()
            }
            forecast = mapper.mapFiveDaysForecastFromDto(response.body())?.list ?: throw Exception()
        } catch (e: Exception) {
            throw e
        }
        return forecast
    }
}