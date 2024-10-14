package com.grigorev.weatherapp.data

import com.grigorev.weatherapp.data.dto.CurrentWeatherDto
import com.grigorev.weatherapp.data.dto.FiveDaysForecastDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("weather?id=$ST_PETERSBURG_ID&appid=$API_KEY&units=metric")
    suspend fun getCurrentWeather(): Response<CurrentWeatherDto>

    @GET("forecast?id=$ST_PETERSBURG_ID&appid=$API_KEY&units=metric")
    suspend fun getFiveDaysForecast(): Response<FiveDaysForecastDto>

    companion object {
        const val ST_PETERSBURG_ID = 498817
        const val API_KEY = "d9e6fe2ca9bd114df14262b014663852"
    }
}