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

    private val dummyPhoneNumberValue: String = "0812345789"
    private val dummyPasswordValue: String = "1234"

    /**
     * Check login success value
     */
    fun login(phoneNumber: String, password: String) {
        if (phoneNumber == dummyPhoneNumberValue && password == dummyPasswordValue) {
            viewModelScope.launch {
                userRepository.setLoginStatus(true)
                userRepository.setShouldShowOnboard(false)
                _loginSuccess.value = true
            }
        } else {
            _loginSuccess.value = false
        }
    }
}