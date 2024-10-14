package com.grigorev.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.domain.GetFiveDaysForecastUseCase
import com.grigorev.weatherapp.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

val emptyForecast = FiveDaysForecast(list = emptyList())

class ForecastViewModel @Inject constructor(
    private val getFiveDaysForecastUseCase: GetFiveDaysForecastUseCase
) : ViewModel() {

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