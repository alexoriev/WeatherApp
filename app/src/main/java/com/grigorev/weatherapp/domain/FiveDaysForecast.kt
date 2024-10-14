package com.grigorev.weatherapp.domain

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
    val clouds: CloudsForecast? = null,
    val dt: Int = 0,
    val dt_txt: String = "",
    val main: MainForecast? = null,
    val pop: Double = 0.0,
    val visibility: Int = 0,
    val weather: List<WeatherForecast> = emptyList(),
    val wind: WindForecast? = null
)

data class WindForecast(
    val deg: Int = 0,
    val gust: Double = 0.0,
    val speed: Double = 0.0
)

data class WeatherForecast(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)

data class CloudsForecast(
    val all: Int = 0
)

data class MainForecast(
    val feels_like: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val temp_max: Double = 0.0,
    val temp_min: Double = 0.0
)