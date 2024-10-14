package com.grigorev.weatherapp.domain

data class CurrentWeather(
    val clouds: Clouds?,
    val dt: Int?,
    val main: Main?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather> = emptyList(),
    val wind: Wind?
)

data class Sys(
    val sunrise: Int?,
    val sunset: Int?
)

data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)

data class Weather(
    val description: String?,
    val icon: String?,
    val main: String?
)

data class Clouds(
    val all: Int?
)

data class Main(
    val feelsLike: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?
)