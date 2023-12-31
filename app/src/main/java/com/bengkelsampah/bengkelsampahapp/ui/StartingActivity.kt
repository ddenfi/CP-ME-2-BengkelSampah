package com.bengkelsampah.bengkelsampahapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteResourceDao
import com.bengkelsampah.bengkelsampahapp.domain.model.UserPreferencesDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserRole
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import com.bengkelsampah.bengkelsampahapp.ui.auth.LoginActivity
import com.bengkelsampah.bengkelsampahapp.ui.driver.DriverMainActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity
import com.bengkelsampah.bengkelsampahapp.ui.onboarding.OnboardingActivity
import com.bengkelsampah.bengkelsampahapp.ui.starting.StartingViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StartingActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private val viewModel:StartingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        lifecycleScope.launch {
            launch {
                viewModel.getUserPreferences().collect{
                    navigate(it)
                }
            }
        }

    }

    private fun navigate(userPreference:UserPreferencesDataModel) {
        val isLogin = userPreference.isLogin
        val showOnBoarding = userPreference.shouldShowOnboard
        val userRole = userPreference.userRole

        intent = if (showOnBoarding) {
            Intent(this@StartingActivity, OnboardingActivity::class.java)
        } else if (isLogin) {
            when (userRole){
                UserRole.CONSUMER -> {
                    Intent(this, MainActivity::class.java)
                }
                UserRole.DRIVER -> {
                    Intent(this, DriverMainActivity::class.java)
                }
            }
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}