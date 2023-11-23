package com.bengkelsampah.bengkelsampahapp.utils

import java.text.NumberFormat
import java.util.Locale

object CurrencyNumberFormat {
    fun convertToCurrencyFormat(number: Int): String {
        val indonesia = Locale("in", "ID")
        val nf = NumberFormat.getInstance(indonesia)
        return nf.format(number)
    }

    fun convertToCurrencyFormat(number: Long): String {
        val indonesia = Locale("in", "ID")
        val nf = NumberFormat.getInstance(indonesia)
        return nf.format(number)
    }
}