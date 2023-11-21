package com.bengkelsampah.bengkelsampahapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

object DateHelper {
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val datePattern = SimpleDateFormat("d MMMM yyyy HH:mm")
        return datePattern.format(Calendar.getInstance().time)
    }

    @SuppressLint("SimpleDateFormat")
    fun get3DaysLaterDate(): String {
        val datePattern = SimpleDateFormat("d MMMM yyyy HH:mm")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 3)
        return datePattern.format(calendar.time)
    }
}