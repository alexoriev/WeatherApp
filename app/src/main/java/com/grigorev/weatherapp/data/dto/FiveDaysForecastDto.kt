package com.grigorev.weatherapp.data.dto

data class FiveDaysForecastDto(
    val list: List<ForecastDto> = emptyList(),
)

data class ForecastDto(
    val dt: Int?,
    val main: MainForecastDto?,
    val weather: List<WeatherForecastDto> = emptyList(),
    val wind: WindForecastDto?
)

data class WindForecastDto(
    val speed: Double?
)

data class WeatherForecastDto(
    val icon: String?,
    val main: String?
)

data class MainForecastDto(
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
)