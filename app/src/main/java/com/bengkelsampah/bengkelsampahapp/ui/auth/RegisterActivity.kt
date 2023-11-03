package com.bengkelsampah.bengkelsampahapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
            val email = binding.tietEmailRegister.text.toString()
            val password = binding.tietPasswordRegister.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(name, email, password)
            } else {
                Toast.makeText(this, "Nama, email, dan password harus diisi!", Toast.LENGTH_SHORT)
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
                Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}