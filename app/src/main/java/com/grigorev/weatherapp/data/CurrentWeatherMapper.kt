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
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor() {
    fun mapCurrentWeatherFromDto(dto: CurrentWeatherDto?): CurrentWeather? = dto?.let {
        CurrentWeather(
            description = mapDescriptionFromDto(it.weather),
            iconUrl = mapIconUrlFromDto(it.weather),
            clouds = mapCloudsFromDto(it.clouds),
            dateTime = it.dt?.let { dateTime -> TimeConverter.formatUnixTimeToDateTime(dateTime) },
            main = mapMainFromDto(it.main),
            sys = mapSysFromDto(it.sys),
            visibility = it.visibility.toString(),
            weather = mapWeatherFromDto(it.weather),
            wind = mapWindFromDto(it.wind)
        )
    }

    private fun mapDescriptionFromDto(dto: List<WeatherDto>): String? {
        return if (dto.isNotEmpty()) {
            dto[0].main
        } else null
    }

    private fun mapIconUrlFromDto(dto: List<WeatherDto>): String? {
        return if (dto.isNotEmpty()) {
            "https://openweathermap.org/img/wn/${dto[0].icon}.png"
        } else null
    }

    private fun mapCloudsFromDto(dto: CloudsDto?): Clouds? = dto?.let {
        Clouds(it.all.toString())
    }

    private fun mapMainFromDto(dto: MainDto?): Main? = dto?.let {
        Main(
            feelsLike = it.feelsLike?.toInt().toString(),
            humidity = it.humidity.toString(),
            pressure = it.pressure.toString(),
            temp = it.temp?.toInt().toString()
        )
    }

    private fun mapSysFromDto(dto: SysDto?): Sys? = dto?.let {
        Sys(sunrise = it.sunrise?.let { value -> TimeConverter.formatUnixTimeToTime(value) },
            sunset = it.sunset?.let { value -> TimeConverter.formatUnixTimeToTime(value) })
    }

    private fun mapWeatherFromDto(dto: List<WeatherDto>): List<Weather> {
        return dto.map {
            Weather(description = it.description, icon = it.icon, main = it.main)
        }
    }

    private fun mapWindFromDto(dto: WindDto?): Wind? = dto?.let {
        Wind(
            deg = it.deg.toString(),
            gust = if (it.gust != null) it.gust.toString() else null,
            speed = it.speed.toString()
        )
    }
}