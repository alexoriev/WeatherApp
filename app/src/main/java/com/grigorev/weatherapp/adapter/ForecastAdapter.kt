package com.grigorev.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grigorev.weatherapp.R
import com.grigorev.weatherapp.databinding.ItemForecastBinding
import com.grigorev.weatherapp.dto.Forecast
import com.grigorev.weatherapp.util.TimeConverter

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
            val iconUrl =
                "https://openweathermap.org/img/wn/${forecast.weather[0].icon}.png"

            Glide.with(forecastIcon)
                .load(iconUrl)
                .into(forecastIcon)

            temperature.text = temperature.context.getString(
                R.string.temperature_forecast_item,
                forecast.main?.temp?.toInt().toString()
            )
            forecastText.text = forecast.weather[0].main
            dateTime.text = TimeConverter().formatUnixTimeToDateTime(forecast.dt)
            humidity.text = humidity.context.getString(
                R.string.humidity_text,
                forecast.main?.humidity.toString()
            )
            windSpeed.text = windSpeed.context.getString(
                R.string.wind_speed_text,
                forecast.wind?.speed?.toInt().toString()
            )
            pressure.text = pressure.context.getString(
                R.string.pressure_text,
                forecast.main?.pressure.toString()
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