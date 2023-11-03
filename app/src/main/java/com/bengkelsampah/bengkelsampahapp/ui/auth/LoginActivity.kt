package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityLoginBinding
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)

        performLogin()
        observeLoginResult()
        navigateToRegister()
        navigateToForgotPassword()
    }

    /**
     * Called when user click login button
     */
    private fun performLogin() {
        binding.btnLoginLogin.setOnClickListener {
            val email = binding.tietEmailLogin.text.toString()
            val password = binding.tietPasswordLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(this, "Email dan password harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Observe login result by view model
     */
    private fun observeLoginResult() {
        viewModel.loginSuccess.observe(this) { loginSuccess ->
            if (loginSuccess) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Navigate to register page
     */
    private fun navigateToRegister() {
        binding.tvRegisterLogin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Navigate to forgot password page
     */
    private fun navigateToForgotPassword() {
        binding.tbtnForgotPasswordLogin.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}