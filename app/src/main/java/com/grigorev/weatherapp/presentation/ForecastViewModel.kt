package com.grigorev.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.data.FiveDaysForecastRepositoryImpl
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.domain.Forecast
import com.grigorev.weatherapp.domain.GetFiveDaysForecastUseCase
import com.grigorev.weatherapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val emptyForecast = FiveDaysForecast(
    city = null,
    cnt = 0,
    list = emptyList(),
    message = 0,
)

class ForecastViewModel : ViewModel() {

    //TODO: Implement via DI
    private val repository = FiveDaysForecastRepositoryImpl()
    private val getFiveDaysForecastUseCase = GetFiveDaysForecastUseCase(repository)

    private val _error = SingleLiveEvent<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    var forecast = MutableLiveData(emptyForecast.list)

    fun getForecast() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            try {
                val fiveDaysForecast = getFiveDaysForecastUseCase.getFiveDaysForecast()
                forecast.postValue(fiveDaysForecast)
            } catch (e: Exception) {
                _error.postValue(e)
            }
        }
    }
}