package com.grigorev.weatherapp.domain

data class CurrentWeather(
    val description: String?,
    val iconUrl: String?,
    val clouds: Clouds?,
    val dateTime: String?,
    val main: Main?,
    val sys: Sys?,
    val visibility: String?,
    val weather: List<Weather> = emptyList(),
    val wind: Wind?
)

data class Sys(
    val sunrise: String?,
    val sunset: String?
)

data class Wind(
    val deg: String?,
    val gust: String?,
    val speed: String?
)

data class Weather(
    val description: String?,
    val icon: String?,
    val main: String?
)

data class Clouds(
    val all: String?
)

data class Main(
    val feelsLike: String?,
    val humidity: String?,
    val pressure: String?,
    val temp: String?
)