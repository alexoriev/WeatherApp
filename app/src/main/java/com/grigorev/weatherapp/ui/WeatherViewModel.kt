package com.grigorev.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.dto.CurrentWeather
import com.grigorev.weatherapp.repository.CurrentWeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel: ViewModel() {

    private val repository = CurrentWeatherRepositoryImpl()

    var weather = MutableLiveData(CurrentWeather())

    fun getWeather() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val currentWeather = repository.getCurrentWeather()
            weather.postValue(currentWeather)
        }
    }

}