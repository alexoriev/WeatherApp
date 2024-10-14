package com.grigorev.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grigorev.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val component by lazy {
        (application as WeatherApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}