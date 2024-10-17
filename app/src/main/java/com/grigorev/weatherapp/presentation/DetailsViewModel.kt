package com.grigorev.weatherapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    var weather = MutableLiveData<CurrentWeather>()

    fun getWeather() = viewModelScope.launch(Dispatchers.IO) {
        val currentWeather = getCurrentWeatherUseCase.getCurrentWeather()
        weather.postValue(currentWeather)
    }
}