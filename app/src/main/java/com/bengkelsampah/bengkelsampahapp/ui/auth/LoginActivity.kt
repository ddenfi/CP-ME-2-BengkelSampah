package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityLoginBinding
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        performLogin()
        observeLoginResult()
        navigateToRegister()
        navigateToForgotPassword()
    }

    /**
     * Called when the user clicks the login button
     */
    private fun performLogin() {
        binding.btnLoginLogin.setOnClickListener {
            val phoneNumber = binding.tietPhoneNumberLogin.text.toString()
            val password = binding.tietPasswordLogin.text.toString()

            if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(phoneNumber, password)
            } else {
                showLoginErrorSweetAlert(getString(R.string.all_login_field_must_be_filled))
            }
        }
    }

    /**
     * Observe login result by the view model
     */
    private fun observeLoginResult() {
        viewModel.loginLiveData.observe(this) { loginResource ->
            when (loginResource) {
                is Resource.Loading -> {
                    progressDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText(getString(R.string.loading))
                    progressDialog?.show()
                }

                is Resource.Success -> {
                    progressDialog?.dismiss()
                    viewModel.responseMessage.observe(this) { responseMessage ->
                        showLoginSuccessSweetAlert(responseMessage)
                    }
                }

                is Resource.Error -> {
                    progressDialog?.dismiss()
                    viewModel.responseMessage.observe(this) { responseMessage ->
                        showLoginErrorSweetAlert(responseMessage)
                    }
                }
            }
        }
    }

    /**
     * Show a success SweetAlertDialog for login
     */
    private fun showLoginSuccessSweetAlert(message: String) {
        val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText(getString(R.string.login_successful))
            .setContentText(message)
            .hideConfirmButton()

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismissWithAnimation()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    /**
     * Show an error SweetAlertDialog for login
     */
    private fun showLoginErrorSweetAlert(message: String) {
        val dialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(getString(R.string.login_failed))
            .setContentText(message)
            .hideConfirmButton()

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismissWithAnimation()
        }, 1500)
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