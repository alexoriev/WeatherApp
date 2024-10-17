package com.grigorev.weatherapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

val emptyWeather = CurrentWeather(
    description = null,
    iconUrl = null,
    clouds = null,
    dateTime = null,
    main = null,
    sys = null,
    visibility = null,
    weather = emptyList(),
    wind = null
)

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    var weather = MutableLiveData(emptyWeather)

    fun getWeather() = viewModelScope.launch(Dispatchers.IO) {
        weather.postValue(getCurrentWeatherUseCase.getCurrentWeather())
    }
}