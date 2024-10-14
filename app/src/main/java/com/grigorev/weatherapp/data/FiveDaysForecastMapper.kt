package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.data.dto.FiveDaysForecastDto
import com.grigorev.weatherapp.data.dto.ForecastDto
import com.grigorev.weatherapp.data.dto.MainForecastDto
import com.grigorev.weatherapp.data.dto.WindForecastDto
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.domain.Forecast
import com.grigorev.weatherapp.domain.MainForecast
import com.grigorev.weatherapp.domain.WindForecast

class FiveDaysForecastMapper {
    fun mapFiveDaysForecastFromDto(dto: FiveDaysForecastDto?): FiveDaysForecast? = dto?.let {
        value -> FiveDaysForecast(value.list.map { mapForecastFromDto(it) })
    }

    private fun mapForecastFromDto(dto: ForecastDto): Forecast = Forecast(
        dt = dto.dt,
        main = mapMainForecastFromDto(dto.main),
        wind = mapWindForecastFromDto(dto.wind)
    )

    private fun mapMainForecastFromDto(dto: MainForecastDto?): MainForecast? = dto?.let {
        MainForecast(
            humidity = it.humidity,
            pressure = it.pressure,
            temp = it.temp
        )
    }

    private fun mapWindForecastFromDto(dto: WindForecastDto?): WindForecast? = dto?.let {
        WindForecast(it.speed)
    }

}

