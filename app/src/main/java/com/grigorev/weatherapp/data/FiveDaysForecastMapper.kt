package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.data.dto.FiveDaysForecastDto
import com.grigorev.weatherapp.data.dto.ForecastDto
import com.grigorev.weatherapp.data.dto.MainForecastDto
import com.grigorev.weatherapp.data.dto.WeatherForecastDto
import com.grigorev.weatherapp.data.dto.WindForecastDto
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.domain.Forecast
import com.grigorev.weatherapp.domain.MainForecast
import com.grigorev.weatherapp.domain.WindForecast
import javax.inject.Inject

class FiveDaysForecastMapper @Inject constructor() {
    fun mapFiveDaysForecastFromDto(dto: FiveDaysForecastDto?): FiveDaysForecast? =
        dto?.let { value ->
            FiveDaysForecast(value.list.map { mapForecastFromDto(it) })
        }

    private fun mapForecastFromDto(dto: ForecastDto): Forecast = Forecast(
        dateTime = dto.dt?.let { TimeConverter.formatUnixTimeToDateTime(it) },
        main = mapMainForecastFromDto(dto.main),
        iconUrl = mapIconUrlFromDto(dto.weather),
        description = mapDescriptionFromDto(dto.weather),
        wind = mapWindForecastFromDto(dto.wind)
    )

    private fun mapMainForecastFromDto(dto: MainForecastDto?): MainForecast? = dto?.let {
        MainForecast(
            humidity = it.humidity.toString(),
            pressure = it.pressure.toString(),
            temp = it.temp?.toInt().toString()
        )
    }

    private fun mapIconUrlFromDto(dto: List<WeatherForecastDto>): String? {
        return if (dto.isNotEmpty()) {
            "https://openweathermap.org/img/wn/${dto[0].icon}.png"
        } else null
    }

    private fun mapDescriptionFromDto(dto: List<WeatherForecastDto>): String? {
        return if (dto.isNotEmpty()) {
            dto[0].main
        } else null
    }

    private fun mapWindForecastFromDto(dto: WindForecastDto?): WindForecast? = dto?.let {
        WindForecast(it.speed?.toInt().toString())
    }
}

