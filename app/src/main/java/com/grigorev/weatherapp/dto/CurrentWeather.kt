package com.grigorev.weatherapp.dto

data class CurrentWeather(
    val clouds: Clouds? = null,
    val dt: Int = 0,
    val id: Int = 0,
    val main: Main? = null,
    val name: String = "",
    val sys: Sys? = null,
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: List<Weather> = emptyList(),
    val wind: Wind? = null
)

data class Clouds(
    val all: Int = 0
)

data class Main(
    val feels_like: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val temp_max: Double = 0.0,
    val temp_min: Double = 0.0
)

data class Weather(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)

data class Wind(
    val deg: Int = 0,
    val gust: Double = 0.0,
    val speed: Double = 0.0
)

data class Sys(
    val sunrise: Int = 0,
    val sunset: Int = 0
)