package com.bengkelsampah.bengkelsampahapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    /**
     * Check login success value
     */
    fun login(email: String, password: String) {
        if (email == "test@gmail.com" && password == "1234") {
            viewModelScope.launch {
                userRepository.setLoginStatus(true)
                _loginSuccess.value = true
            }
        } else {
            _loginSuccess.value = false
        }
    }
}