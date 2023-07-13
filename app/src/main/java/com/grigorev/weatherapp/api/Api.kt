package com.grigorev.weatherapp.api

import com.grigorev.weatherapp.dto.CurrentWeather
import com.grigorev.weatherapp.dto.FiveDaysForecast
import retrofit2.Response
import retrofit2.http.GET

private const val ST_PETERSBURG_ID = 498817
private const val API_KEY = "d9e6fe2ca9bd114df14262b014663852"

interface Api {

    @GET("weather?id=${ST_PETERSBURG_ID}&appid=${API_KEY}&units=metric")
    suspend fun getCurrentWeather(): Response<CurrentWeather>

    @GET("forecast?id=${ST_PETERSBURG_ID}&appid=${API_KEY}&units=metric")
    suspend fun getFiveDaysForecast(): Response<FiveDaysForecast>

}