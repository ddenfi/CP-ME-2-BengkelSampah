package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
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
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Email dan password harus diisi!")
                    .show()
            }
        }
    }

    /**
     * Observe login result by view model
     */
    private fun observeLoginResult() {
        viewModel.loginSuccess.observe(this) { loginSuccess ->
            if (loginSuccess) {
                val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login Berhasil")

                dialog.setConfirmClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }

                dialog.setOnDismissListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                dialog.show()
            } else {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Email atau password salah")
                    .show()
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