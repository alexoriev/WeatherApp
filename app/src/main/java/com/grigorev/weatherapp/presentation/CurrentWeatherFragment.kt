package com.grigorev.weatherapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<CurrentWeatherViewModel> { viewModelFactory }

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
        _binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}