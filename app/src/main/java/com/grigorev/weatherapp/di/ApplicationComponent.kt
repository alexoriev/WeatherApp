package com.grigorev.weatherapp.di

import android.app.Application
import com.grigorev.weatherapp.presentation.CurrentWeatherFragment
import com.grigorev.weatherapp.presentation.DetailsFragment
import com.grigorev.weatherapp.presentation.ForecastFragment
import com.grigorev.weatherapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CurrentWeatherFragment)

    fun inject(fragment: DetailsFragment)

    fun inject(fragment: ForecastFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}