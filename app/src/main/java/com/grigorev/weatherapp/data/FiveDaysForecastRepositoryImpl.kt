package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.domain.FiveDaysForecastRepository
import com.grigorev.weatherapp.domain.Forecast
import javax.inject.Inject

class FiveDaysForecastRepositoryImpl @Inject constructor(
    private val apiService: Api,
    private val mapper: FiveDaysForecastMapper
) : FiveDaysForecastRepository {

    override suspend fun getFiveDaysForecast(): List<Forecast> {
        lateinit var forecast: List<Forecast>
        try {
            val response = apiService.getFiveDaysForecast()
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