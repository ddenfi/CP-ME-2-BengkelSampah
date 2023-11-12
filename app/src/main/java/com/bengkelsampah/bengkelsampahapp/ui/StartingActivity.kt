package com.bengkelsampah.bengkelsampahapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.domain.model.UserPreferencesDataModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import com.bengkelsampah.bengkelsampahapp.ui.auth.LoginActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity
import com.bengkelsampah.bengkelsampahapp.ui.onboarding.OnboardingActivity
import com.bengkelsampah.bengkelsampahapp.ui.starting.StartingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartingActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private val viewModel:StartingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        lifecycleScope.launch {
            viewModel.getUserPreferences().collect{
                navigate(it)
            }
        }

    }

    private fun navigate(userPreference:UserPreferencesDataModel) {
        val isLogin = userPreference.isLogin
        val showOnBoarding = userPreference.shouldShowOnboard

        intent = if (showOnBoarding) {
            Intent(this@StartingActivity, OnboardingActivity::class.java)
        } else if (isLogin) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}