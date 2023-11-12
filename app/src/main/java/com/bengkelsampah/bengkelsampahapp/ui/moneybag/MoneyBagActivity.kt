package com.bengkelsampah.bengkelsampahapp.ui.moneybag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityMoneyBagBinding

class MoneyBagActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMoneyBagBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyBagBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}