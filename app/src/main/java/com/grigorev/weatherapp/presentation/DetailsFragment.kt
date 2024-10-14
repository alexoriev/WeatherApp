package com.grigorev.weatherapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        lifecycleScope.launch {
            viewModel.getWeather().join()
        }

        viewModel.weather.observe(viewLifecycleOwner) { currentWeather ->
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
                windGust.text = if (currentWeather.wind?.gust != null) {
                    context?.getString(
                        R.string.wind_gust_text,
                        currentWeather.wind.gust
                    )
                } else "n/a"
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
}