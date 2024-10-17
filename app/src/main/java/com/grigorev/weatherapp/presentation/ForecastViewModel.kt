package com.grigorev.weatherapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.weatherapp.domain.FiveDaysForecast
import com.grigorev.weatherapp.domain.GetFiveDaysForecastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

val emptyForecast = FiveDaysForecast(list = emptyList())

class ForecastViewModel @Inject constructor(
    private val getFiveDaysForecastUseCase: GetFiveDaysForecastUseCase
) : ViewModel() {

    var forecast = MutableLiveData(emptyForecast.list)

    fun getForecast() = viewModelScope.launch(Dispatchers.IO) {
        forecast.postValue(getFiveDaysForecastUseCase.getFiveDaysForecast())
    }
}