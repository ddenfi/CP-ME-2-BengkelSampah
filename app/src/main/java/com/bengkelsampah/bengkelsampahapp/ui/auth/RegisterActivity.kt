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
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
                showRegisterErrorSweetAlert(getString(R.string.all_register_field_must_be_filled))
            }
        }
    }

    /**
     * Observe register result by view model
     */
    private fun observeRegisterResult() {
        viewModel.registerLiveData.observe(this) { registerResource ->
            when (registerResource) {
                is Resource.Loading -> {
                    progressDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText(getString(R.string.loading))
                    progressDialog?.show()
                }

                is Resource.Success -> {
                    progressDialog?.dismiss()
                    viewModel.responseMessage.observe(this) { responseMessage ->
                        showRegisterSuccessSweetAlert(responseMessage)
                    }
                }

                is Resource.Error -> {
                    progressDialog?.dismiss()
                    viewModel.responseMessage.observe(this) { responseMessage ->
                        showRegisterErrorSweetAlert(responseMessage)
                    }
                }
            }
        }
    }

    /**
     * Show a success SweetAlertDialog for register
     */
    private fun showRegisterSuccessSweetAlert(message: String) {
        val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText(getString(R.string.register_successful))
            .setContentText(message)
            .hideConfirmButton()

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    /**
     * Show an error SweetAlertDialog for register
     */
    private fun showRegisterErrorSweetAlert(message: String) {
        val dialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(getString(R.string.register_failed))
            .setContentText(message)
            .hideConfirmButton()

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismissWithAnimation()
        }, 1500)
    }
}