package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        setContentView(binding.root)

        performRegister()
        observeRegisterResult()
    }

    /**
     * Called when user click register button
     */
    private fun performRegister() {
        binding.btnRegisterRegister.setOnClickListener {
            val name = binding.tietNameRegister.text.toString()
            val phoneNumber = binding.tietPhoneNumberRegister.text.toString()
            val password = binding.tietPasswordRegister.text.toString()

            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(name, phoneNumber, password)
            } else {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.all_register_field_must_be_filled))
                    .show()
            }
        }
    }

    /**
     * Observe register result by view model
     */
    private fun observeRegisterResult() {
        viewModel.registerSuccess.observe(this) { registerSuccess ->
            if (registerSuccess) {
                val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(getString(R.string.register_successful))

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