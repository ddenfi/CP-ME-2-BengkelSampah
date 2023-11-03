package com.bengkelsampah.bengkelsampahapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    /**
     * Check login success value
     */
    fun login(email: String, password: String) {
        if (email == "test@gmail.com" && password == "1234") {
            _loginSuccess.value = true
        } else {
            _loginSuccess.value = false
        }
    }
}