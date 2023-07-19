package com.grigorev.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.weatherapp.adapter.ForecastAdapter
import com.grigorev.weatherapp.databinding.FragmentForecastBinding
import com.grigorev.weatherapp.viewmodel.WeatherViewModel
import com.grigorev.weatherapp.viewmodel.emptyForecast
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            if (viewModel.forecast.value == emptyForecast.list) {
                viewModel.getForecast().join()
            }

            val adapter = ForecastAdapter()

            binding.forecastList.adapter = adapter

            viewModel.forecast.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
        return binding.root
    }
}