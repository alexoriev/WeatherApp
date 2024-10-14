package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.data.dto.CloudsDto
import com.grigorev.weatherapp.data.dto.CurrentWeatherDto
import com.grigorev.weatherapp.data.dto.MainDto
import com.grigorev.weatherapp.data.dto.SysDto
import com.grigorev.weatherapp.data.dto.WeatherDto
import com.grigorev.weatherapp.data.dto.WindDto
import com.grigorev.weatherapp.domain.Clouds
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.Main
import com.grigorev.weatherapp.domain.Sys
import com.grigorev.weatherapp.domain.Weather
import com.grigorev.weatherapp.domain.Wind

class CurrentWeatherMapper {
    fun mapCurrentWeatherFromDto(dto: CurrentWeatherDto?): CurrentWeather? = dto?.let {
        CurrentWeather(
            clouds = mapCloudsFromDto(it.clouds),
            dt = it.dt,
            main = mapMainFromDto(it.main),
            sys = mapSysFromDto(it.sys),
            visibility = it.visibility,
            weather = mapWeatherFromDto(it.weather),
            wind = mapWindFromDto(it.wind))
    }

    private fun mapCloudsFromDto(dto: CloudsDto?): Clouds? = dto?.let {
        Clouds(it.all)
    }

    private fun mapMainFromDto(dto: MainDto?): Main? = dto?.let {
        Main(
            feelsLike = it.feelsLike,
            humidity = it.humidity,
            pressure = it.pressure,
            temp = it.temp
        )
    }

    private fun mapSysFromDto(dto: SysDto?): Sys? = dto?.let {
        Sys(sunrise = it.sunrise, sunset = it.sunset)
    }

    private fun mapWeatherFromDto(dto: List<WeatherDto>): List<Weather> {
        return dto.map {
            Weather(description = it.description, icon = it.icon, main = it.main)
        }
    }

    private fun mapWindFromDto(dto: WindDto?): Wind? = dto?.let {
        Wind(
            deg = it.deg,
            gust = it.gust,
            speed = it.speed)
    }
}