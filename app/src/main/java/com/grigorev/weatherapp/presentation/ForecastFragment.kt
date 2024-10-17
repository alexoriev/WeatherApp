package com.grigorev.weatherapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.weatherapp.databinding.FragmentForecastBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ForecastViewModel> { viewModelFactory }

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
        _binding = FragmentForecastBinding.inflate(layoutInflater)

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}