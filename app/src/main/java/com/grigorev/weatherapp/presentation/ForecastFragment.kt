package com.grigorev.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.weatherapp.databinding.FragmentForecastBinding
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val viewModel: ForecastViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            if (viewModel.forecast.value == emptyForecast) {
                viewModel.getForecast().join()
            } else {
                binding.progressBar.visibility = View.GONE
            }

            val adapter = ForecastAdapter()
            binding.forecastList.adapter = adapter
            viewModel.forecast.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}