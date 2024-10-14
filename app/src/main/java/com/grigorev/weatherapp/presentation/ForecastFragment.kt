package com.grigorev.weatherapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.grigorev.weatherapp.databinding.FragmentForecastBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var viewModel: ForecastViewModel

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
        binding = FragmentForecastBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, viewModelFactory)[ForecastViewModel::class.java]

        viewModel.forecast.observe(viewLifecycleOwner) {
            if (it == emptyForecast.list) {
                binding.progressBar.visibility = View.VISIBLE
                lifecycleScope.launch {
                    viewModel.getForecast().join()
                }
            } else {
                binding.progressBar.visibility = View.GONE
            }

            val adapter = ForecastAdapter()
            binding.forecastList.adapter = adapter
            adapter.submitList(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}