package com.grigorev.weatherapp.domain

import javax.inject.Inject

class GetFiveDaysForecastUseCase @Inject constructor(
    private val fiveDaysForecastRepository: FiveDaysForecastRepository
) {

    suspend fun getFiveDaysForecast(): List<Forecast> {
        return fiveDaysForecastRepository.getFiveDaysForecast()
    }
}