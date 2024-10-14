package com.grigorev.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.data.CurrentWeatherRepositoryImpl
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.GetCurrentWeatherUseCase
import com.grigorev.weatherapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

class CurrentWeatherViewModel : ViewModel() {

    //TODO: Implement via DI
    private val repository = CurrentWeatherRepositoryImpl()
    private val getCurrentWeatherUseCase = GetCurrentWeatherUseCase(repository)

    private val _error = SingleLiveEvent<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    var weather = MutableLiveData(emptyWeather)

    fun getWeather() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            try {
                val currentWeather = getCurrentWeatherUseCase.getCurrentWeather()
                weather.postValue(currentWeather)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }
}