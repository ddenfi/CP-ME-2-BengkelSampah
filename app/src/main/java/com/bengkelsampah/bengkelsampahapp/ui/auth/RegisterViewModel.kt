package com.bengkelsampah.bengkelsampahapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    /**
     * Check register success value
     */
    fun register(name: String, email: String, password: String) {
        _registerSuccess.value = true
    }
}