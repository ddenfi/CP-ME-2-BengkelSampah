package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        setContentView(binding.root)

        performForgotPassword()
        observeForgotPasswordResult()
    }

    /**
     * Called when user click reset password button
     */
    private fun performForgotPassword() {
        binding.btnSendLinkForgotPassword.setOnClickListener {
            val email = binding.tietEmailForgotPassword.text.toString()
            if (email.isNotEmpty()) {
                viewModel.forgotPassword(email)
            } else {
                Toast.makeText(this, "Email harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Observe forgot password result by view model
     */
    private fun observeForgotPasswordResult() {
        viewModel.forgotPasswordSuccess.observe(this) { forgotPasswordSuccess ->
            if (forgotPasswordSuccess) {
                Toast.makeText(
                    this,
                    "Link reset password telah dikirimkan melalui email",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}