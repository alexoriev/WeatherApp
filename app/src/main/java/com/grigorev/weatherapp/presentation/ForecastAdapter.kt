package com.grigorev.weatherapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.ItemForecastBinding
import com.grigorev.weatherapp.domain.Forecast

class ForecastAdapter : ListAdapter<Forecast, ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }
}

@SuppressLint("StringFormatInvalid")
class ViewHolder(private val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        binding.apply {
            val iconUrl = forecast.iconUrl

            Glide.with(forecastIcon)
                .load(iconUrl)
                .into(forecastIcon)

            temperature.text = temperature.context.getString(
                R.string.temperature_forecast_item,
                forecast.main?.temp
            )
            forecastText.text = forecast.description
            dateTime.text = forecast.dateTime
            humidity.text = humidity.context.getString(
                R.string.humidity_text,
                forecast.main?.humidity
            )
            windSpeed.text = windSpeed.context.getString(
                R.string.wind_speed_text,
                forecast.wind?.speed
            )
            pressure.text = pressure.context.getString(
                R.string.pressure_text,
                forecast.main?.pressure
            )
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.weather == newItem.weather
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }
}