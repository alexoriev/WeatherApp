package com.grigorev.weatherapp.dto

data class CurrentWeather(
    val base: String = "",
    val clouds: Clouds? = null,
    val cod: Int = 0,
    val coord: Coord? = null,
    val dt: Int = 0,
    val id: Int = 0,
    val main: Main? = null,
    val name: String = "",
    val sys: Sys? = null,
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)

data class Clouds(
    val all: Int = 0
)

data class Coord(
    val lat: Double = 0.0,
    val lon: Double = 0.0
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