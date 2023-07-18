package com.grigorev.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.grigorev.weatherapp.util.TimeConverter
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    @SuppressLint("StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.getWeather().join()
            val currentWeather = viewModel.weather.value

            binding.apply {
                dateTime.text = TimeConverter().formatUnixTimeToDateTime(currentWeather!!.dt)
                weatherDescription.text = currentWeather.weather?.get(0)?.description
                temperature.text = currentWeather.main?.temp?.toInt().toString()
                feelsLike.text = context?.getString(
                    R.string.feels_like,
                    currentWeather.main?.feels_like?.toInt().toString()
                )
                humidity.text = context?.getString(
                    R.string.humidity_text,
                    currentWeather.main?.humidity.toString(),
                )
                windSpeed.text = context?.getString(
                    R.string.wind_speed_text,
                    currentWeather.wind?.speed.toString(),
                )
                pressure.text = context?.getString(
                    R.string.pressure_text,
                    currentWeather.main?.pressure.toString(),
                )

                val iconUrl =
                    "https://openweathermap.org/img/wn/${currentWeather.weather?.get(0)?.icon}.png"

                Glide.with(weatherIcon)
                    .load(iconUrl)
                    .into(weatherIcon)

                buttonDetails.setOnClickListener {
                    findNavController().navigate(R.id.detailsFragment)
                }
            }
        }
        return binding.root
    }
}