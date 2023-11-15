package com.bengkelsampah.bengkelsampahapp.ui.driver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityDriverMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDriverMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val driverNavHostFragment = supportFragmentManager.findFragmentById(R.id.driver_screen_navhost) as NavHostFragment
        binding.bottomNavDriver.setupWithNavController(driverNavHostFragment.navController)
    }
}