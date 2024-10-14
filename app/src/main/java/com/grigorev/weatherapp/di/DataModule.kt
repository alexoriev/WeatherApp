package com.grigorev.weatherapp.di

import com.grigorev.weatherapp.BuildConfig
import com.grigorev.weatherapp.data.Api
import com.grigorev.weatherapp.data.CurrentWeatherRepositoryImpl
import com.grigorev.weatherapp.data.FiveDaysForecastRepositoryImpl
import com.grigorev.weatherapp.domain.CurrentWeatherRepository
import com.grigorev.weatherapp.domain.FiveDaysForecastRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun bindCurrentWeatherRepository(impl: CurrentWeatherRepositoryImpl): CurrentWeatherRepository {
        return impl
    }

    @Provides
    fun bindFiveDaysForecastRepository(impl: FiveDaysForecastRepositoryImpl): FiveDaysForecastRepository {
        return impl
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}