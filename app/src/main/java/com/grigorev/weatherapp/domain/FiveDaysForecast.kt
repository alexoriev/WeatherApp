package com.grigorev.weatherapp.domain

data class FiveDaysForecast(
    val list: List<Forecast> = emptyList(),
)

data class Forecast(
    val dateTime: String?,
    val main: MainForecast?,
    val iconUrl: String?,
    val description: String?,
    val weather: List<WeatherForecast> = emptyList(),
    val wind: WindForecast?
)

data class WindForecast(
    val speed: String?
)

data class WeatherForecast(
    val icon: String?,
    val main: String?
)

data class MainForecast(
    val humidity: String?,
    val pressure: String?,
    val temp: String?,
)