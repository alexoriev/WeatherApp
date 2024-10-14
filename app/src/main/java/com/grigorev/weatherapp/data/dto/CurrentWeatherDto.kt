package com.grigorev.weatherapp.data.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    val clouds: CloudsDto?,
    val dt: Int?,
    val main: MainDto?,
    val sys: SysDto?,
    val visibility: Int?,
    val weather: List<WeatherDto> = emptyList(),
    val wind: WindDto?
)

data class SysDto(
    val sunrise: Int?,
    val sunset: Int?
)

data class WindDto(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)

data class WeatherDto(
    val description: String?,
    val icon: String?,
    val main: String?
)

data class CloudsDto(
    val all: Int?
)

data class MainDto(
    @SerializedName("feels_like")
    val feelsLike: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?
)