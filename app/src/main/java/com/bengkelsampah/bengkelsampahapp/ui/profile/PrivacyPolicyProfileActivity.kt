package com.bengkelsampah.bengkelsampahapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPrivacyPolicyProfileBinding

class PrivacyPolicyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}