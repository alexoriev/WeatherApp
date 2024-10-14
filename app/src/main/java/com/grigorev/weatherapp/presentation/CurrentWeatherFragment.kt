package com.grigorev.weatherapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            if (viewModel.weather.value == emptyWeather) {
                binding.celsius.visibility = View.INVISIBLE
                viewModel.getWeather().join()
            }

            val currentWeather = viewModel.weather.value

            viewModel.error.observe(viewLifecycleOwner) {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }

            if (currentWeather != emptyWeather) {
                binding.apply {
                    binding.progressBar.visibility = View.GONE
                    binding.celsius.visibility = View.VISIBLE
                    dateTime.text = currentWeather?.dateTime
                    weatherDescription.text = currentWeather?.description
                    temperature.text = currentWeather?.main?.temp
                    feelsLike.text = context?.getString(
                        R.string.feels_like,
                        currentWeather?.main?.feelsLike
                    )
                    humidity.text = context?.getString(
                        R.string.humidity_text,
                        currentWeather?.main?.humidity
                    )
                    windSpeed.text = context?.getString(
                        R.string.wind_speed_text,
                        currentWeather?.wind?.speed,
                    )
                    pressure.text = context?.getString(
                        R.string.pressure_text,
                        currentWeather?.main?.pressure,
                    )

                    val iconUrl = currentWeather?.iconUrl

                    Glide.with(weatherIcon)
                        .load(iconUrl)
                        .into(weatherIcon)

                    buttonDetails.setOnClickListener {
                        findNavController().navigate(R.id.detailsFragment)
                    }

                    buttonForecast.setOnClickListener {
                        findNavController().navigate(R.id.forecastFragment)
                    }
                }
            }
        }
    }
}