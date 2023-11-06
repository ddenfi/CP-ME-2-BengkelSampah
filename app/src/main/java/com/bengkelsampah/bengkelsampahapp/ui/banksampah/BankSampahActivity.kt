package com.bengkelsampah.bengkelsampahapp.ui.banksampah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityBankSampahBinding

class BankSampahActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBankSampahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankSampahBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}