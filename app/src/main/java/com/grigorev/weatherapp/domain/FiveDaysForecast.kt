package com.grigorev.weatherapp.domain

data class FiveDaysForecast(
    val list: List<Forecast> = emptyList(),
)

data class Forecast(
    val dt: Int?,
    val main: MainForecast?,
    val weather: List<WeatherForecast> = emptyList(),
    val wind: WindForecast?
)

data class WindForecast(
    val speed: Double?
)

data class WeatherForecast(
    val icon: String?,
    val main: String?
)

data class MainForecast(
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
)