package com.grigorev.weatherapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherFragment : Fragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private lateinit var viewModel: CurrentWeatherViewModel

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
        binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory)[CurrentWeatherViewModel::class.java]

        lifecycleScope.launch {
            binding.visibilityGroup.visibility = View.INVISIBLE
            viewModel.getWeather().join()

            viewModel.weather.observe(viewLifecycleOwner) {
                binding.apply {
                    progressBar.visibility = View.GONE
                    visibilityGroup.visibility = View.VISIBLE
                    dateTime.text = it?.dateTime
                    weatherDescription.text = it?.description
                    temperature.text = it?.main?.temp
                    feelsLike.text = context?.getString(
                        R.string.feels_like,
                        it?.main?.feelsLike
                    )
                    humidity.text = context?.getString(
                        R.string.humidity_text,
                        it?.main?.humidity
                    )
                    windSpeed.text = context?.getString(
                        R.string.wind_speed_text,
                        it?.wind?.speed,
                    )
                    pressure.text = context?.getString(
                        R.string.pressure_text,
                        it?.main?.pressure,
                    )

                    val iconUrl = it?.iconUrl

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

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }
}