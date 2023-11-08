package com.bengkelsampah.bengkelsampahapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityProfileBinding

class MainProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
