package com.grigorev.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.dto.CurrentWeather
import com.grigorev.weatherapp.dto.FiveDaysForecast
import com.grigorev.weatherapp.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val emptyWeather = CurrentWeather(
    clouds = null,
    dt = 0,
    id = 0,
    main = null,
    name = "",
    sys = null,
    timezone = 0,
    visibility = 0,
    weather = emptyList(),
    wind = null
)

val emptyForecast = FiveDaysForecast(
    city = null,
    cnt = 0,
    list = emptyList(),
    message = 0,
)

class WeatherViewModel: ViewModel() {

    private val repository = WeatherRepositoryImpl()

    var weather = MutableLiveData(emptyWeather)

    fun getWeather() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val currentWeather = repository.getCurrentWeather()
            weather.postValue(currentWeather)
        }
    }

    var forecast = MutableLiveData(emptyForecast.list)

    fun getForecast() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val fiveDaysForecast = repository.getFiveDaysForecast()
            forecast.postValue(fiveDaysForecast)
        }
    }
}