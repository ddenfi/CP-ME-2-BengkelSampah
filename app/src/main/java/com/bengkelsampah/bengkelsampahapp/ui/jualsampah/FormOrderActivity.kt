package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bengkelsampah.bengkelsampahapp.R

class FormOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_order)
    }

    companion object {
        const val PARTNER_ID = "partner_id"
    }
}