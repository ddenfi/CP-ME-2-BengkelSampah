package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityOnboadingBinding
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPickupBinding

class PickupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}