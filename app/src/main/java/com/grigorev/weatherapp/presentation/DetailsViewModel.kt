package com.grigorev.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.GetCurrentWeatherUseCase
import com.grigorev.weatherapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _error = SingleLiveEvent<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    var weather = MutableLiveData<CurrentWeather>()

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