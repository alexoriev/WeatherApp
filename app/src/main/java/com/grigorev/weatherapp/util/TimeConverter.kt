package com.grigorev.weatherapp.util

import java.util.Locale

class TimeConverter {
    fun formatUnixTime(dateTime: Int): String {
        val sdf = java.text.SimpleDateFormat("MMMM dd, yyyy 'at' HH:mm ", Locale.US)
        val date = java.util.Date(dateTime * 1000L)
        return sdf.format(date)
    }
}