package com.grigorev.weatherapp.domain

class GetFiveDaysForecastUseCase(private val fiveDaysForecastRepository: FiveDaysForecastRepository) {

    suspend fun getFiveDaysForecast(): List<Forecast> {
        return fiveDaysForecastRepository.getFiveDaysForecast()
    }
}