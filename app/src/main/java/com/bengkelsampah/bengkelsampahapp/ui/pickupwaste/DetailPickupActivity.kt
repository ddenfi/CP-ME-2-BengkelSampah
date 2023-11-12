package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityDetailPickupBinding

class DetailPickupActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailPickupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPickupBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}