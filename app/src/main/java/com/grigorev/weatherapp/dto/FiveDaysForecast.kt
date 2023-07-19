package com.grigorev.weatherapp.dto

data class FiveDaysForecast(
    val city: City? = null,
    val cnt: Int = 0,
    val list: List<Forecast> = emptyList(),
    val message: Int = 0
)

data class City(
    val country: String = "",
    val id: Int = 0,
    val name: String = "",
    val population: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val timezone: Int = 0
)

data class Forecast(
    val clouds: Clouds? = null,
    val dt: Int = 0,
    val dt_txt: String = "",
    val main: Main? = null,
    val pop: Double = 0.0,
    val visibility: Int = 0,
    val weather: List<Weather> = emptyList(),
    val wind: Wind? = null
)
