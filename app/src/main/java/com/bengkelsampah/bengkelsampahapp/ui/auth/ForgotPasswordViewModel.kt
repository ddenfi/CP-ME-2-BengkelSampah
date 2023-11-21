package com.bengkelsampah.bengkelsampahapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {
    private val _forgotPasswordSuccess = MutableLiveData<Boolean>()
    val forgotPasswordSuccess: LiveData<Boolean> get() = _forgotPasswordSuccess

    /**
     * Check forgot password success value
     */
    fun forgotPassword(phoneNumber: String) {
        _forgotPasswordSuccess.value = true
    }
}