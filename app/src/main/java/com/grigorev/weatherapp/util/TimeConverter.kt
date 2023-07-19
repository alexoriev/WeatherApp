package com.grigorev.weatherapp.util

import java.util.Locale

class TimeConverter {
    fun formatUnixTimeToDateTime(dateTime: Int): String {
        val sdf = java.text.SimpleDateFormat("EEE, MMMM dd 'at' HH:mm ", Locale.US)
        val date = java.util.Date(dateTime * 1000L)
        return sdf.format(date)
    }

    fun formatUnixTimeToTime(dateTime: Int): String {
        val sdf = java.text.SimpleDateFormat("HH:mm", Locale.US)
        val date = java.util.Date(dateTime * 1000L)
        return sdf.format(date)
    }
}