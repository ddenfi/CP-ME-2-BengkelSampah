package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPickupFromBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.PickupOrderAdapter

class PickupFromActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPickupFromBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickupFromBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}