package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}