package com.grigorev.weatherapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentWeather = viewModel.weather.value

        binding.apply {
            dateTime.text = currentWeather!!.dateTime
            weatherDescription.text = currentWeather.description
            temperature.text = currentWeather.main?.temp
            feelsLike.text = context?.getString(
                R.string.feels_like,
                currentWeather.main?.feelsLike
            )
            humidity.text = context?.getString(
                R.string.humidity_text,
                currentWeather.main?.humidity
            )
            cloudiness.text = context?.getString(
                R.string.cloudiness_text,
                currentWeather.clouds?.all
            )
            visibility.text = context?.getString(
                R.string.visibility_text,
                currentWeather.visibility
            )
            windSpeed.text = context?.getString(
                R.string.wind_speed_text,
                currentWeather.wind?.speed
            )
            windDegrees.text = context?.getString(
                R.string.wind_degrees_text,
                currentWeather.wind?.deg
            )
            windGust.text = context?.getString(
                R.string.wind_gust_text,
                currentWeather.wind?.gust
            )
            pressure.text = context?.getString(
                R.string.pressure_text,
                currentWeather.main?.pressure
            )
            sunrise.text = currentWeather.sys?.sunrise
            sunset.text = currentWeather.sys?.sunset

            val iconUrl = currentWeather.iconUrl

            Glide.with(weatherIcon)
                .load(iconUrl)
                .into(weatherIcon)
        }
    }
}