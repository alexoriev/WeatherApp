package com.grigorev.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.ActivityMainBinding
import com.grigorev.weatherapp.util.TimeConverter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.getWeather().join()
            val currentWeather = viewModel.weather.value

            binding.apply {
                dateTime.text = TimeConverter().formatUnixTime(currentWeather!!.dt)
                weatherDescription.text = currentWeather.weather?.get(0)?.description
                temperature.text = currentWeather.main?.temp?.toInt().toString()
                feelsLike.text = applicationContext.getString(
                    R.string.feels_like,
                    currentWeather.main?.feels_like?.toInt().toString()
                )
                humidity.text = applicationContext.getString(
                    R.string.humidity_text,
                    currentWeather.main?.humidity.toString(),
                )
                windSpeed.text = applicationContext.getString(
                    R.string.wind_speed_text,
                    currentWeather.wind?.speed.toString(),
                )
                pressure.text = applicationContext.getString(
                    R.string.pressure_text,
                    currentWeather.main?.pressure.toString(),
                )
            }
        }
    }
}