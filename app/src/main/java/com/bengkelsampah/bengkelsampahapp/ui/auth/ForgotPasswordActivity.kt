package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
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
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Email harus diisi!")
                    .show()
            }
        }
    }

    /**
     * Observe forgot password result by view model
     */
    private fun observeForgotPasswordResult() {
        viewModel.forgotPasswordSuccess.observe(this) { forgotPasswordSuccess ->
            if (forgotPasswordSuccess) {
                val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Link reset password telah dikirimkan melalui email")

                dialog.setConfirmClickListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }

                dialog.setOnDismissListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                dialog.show()
            }
        }
    }
}