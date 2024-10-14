package com.grigorev.weatherapp.di

import androidx.lifecycle.ViewModel
import com.grigorev.weatherapp.presentation.CurrentWeatherViewModel
import com.grigorev.weatherapp.presentation.DetailsViewModel
import com.grigorev.weatherapp.presentation.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    fun bindCurrentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}