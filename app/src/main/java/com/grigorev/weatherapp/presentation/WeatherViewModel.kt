package com.grigorev.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.CurrentWeather
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.data.WeatherRepositoryImpl
import com.grigorev.weatherapp.util.SingleLiveEvent
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

//TODO: Split into separate LiveData classes for different fragments
class WeatherViewModel : ViewModel() {

    //TODO: Implement via DI
    private val repository = WeatherRepositoryImpl()

    private val _error = SingleLiveEvent<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    var weather = MutableLiveData(emptyWeather)

    var forecast = MutableLiveData(emptyForecast.list)

    fun getWeather() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            try {
                val currentWeather = repository.getCurrentWeather()
                weather.postValue(currentWeather)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }

    fun getForecast() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            try {
                val fiveDaysForecast = repository.getFiveDaysForecast()
                forecast.postValue(fiveDaysForecast)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }

}