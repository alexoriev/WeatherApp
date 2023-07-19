package com.grigorev.weatherapp.ui

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
import com.grigorev.weatherapp.util.TimeConverter
import com.grigorev.weatherapp.viewmodel.WeatherViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: WeatherViewModel by activityViewModels()

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
            dateTime.text = TimeConverter().formatUnixTimeToDateTime(currentWeather!!.dt)
            weatherDescription.text = currentWeather.weather[0].description
            temperature.text = currentWeather.main?.temp?.toInt().toString()
            feelsLike.text = context?.getString(
                R.string.feels_like,
                currentWeather.main?.feels_like?.toInt().toString()
            )
            humidity.text = context?.getString(
                R.string.humidity_text,
                currentWeather.main?.humidity.toString()
            )
            cloudiness.text = context?.getString(
                R.string.cloudiness_text,
                currentWeather.clouds?.all.toString()
            )
            visibility.text = context?.getString(
                R.string.visibility_text,
                currentWeather.visibility.toString()
            )
            windSpeed.text = context?.getString(
                R.string.wind_speed_text,
                currentWeather.wind?.speed.toString()
            )
            windDegrees.text = context?.getString(
                R.string.wind_degrees_text,
                currentWeather.wind?.deg.toString()
            )
            windGust.text = context?.getString(
                R.string.wind_gust_text,
                currentWeather.wind?.gust.toString()
            )
            pressure.text = context?.getString(
                R.string.pressure_text,
                currentWeather.main?.pressure.toString()
            )
            sunrise.text = TimeConverter().formatUnixTimeToTime(currentWeather.sys!!.sunrise)
            sunset.text = TimeConverter().formatUnixTimeToTime(currentWeather.sys.sunset)

            val iconUrl =
                "https://openweathermap.org/img/wn/${currentWeather.weather[0].icon}.png"

            Glide.with(weatherIcon)
                .load(iconUrl)
                .into(weatherIcon)
        }
    }
}