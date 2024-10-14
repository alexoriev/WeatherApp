package com.grigorev.weatherapp.presentation

import android.app.Application
import com.grigorev.weatherapp.di.DaggerApplicationComponent

class WeatherApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}