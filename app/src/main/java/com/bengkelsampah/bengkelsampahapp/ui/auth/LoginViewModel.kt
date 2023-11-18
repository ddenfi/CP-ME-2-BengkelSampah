package com.bengkelsampah.bengkelsampahapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.remote.network.ApiService
import com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth.LoginRequest
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _loginLiveData = MutableLiveData<Resource<Unit>>()
    val loginLiveData: LiveData<Resource<Unit>> get() = _loginLiveData

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage

    /**
     * Check login live data value
     */
    fun login(phoneNumber: String, password: String) {
        _loginLiveData.postValue(Resource.Loading())
        val loginRequest = LoginRequest(phoneNumber, password)
        viewModelScope.launch {
            try {
                val response = apiService.postLogin(loginRequest)
                if (response.isSuccessful) {
                    userRepository.setLoginStatus(true)
                    userRepository.setShouldShowOnboard(false)

                    _loginLiveData.postValue(Resource.Success(Unit))
                    _responseMessage.postValue(response.body()?.message.toString())
                } else {
                    _loginLiveData.postValue(Resource.Error(Throwable("${response.code()} (${response.message()})")))

                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = extractErrorMessage(errorResponse)
                    _responseMessage.postValue(errorMessage ?: R.string.login_failed.toString())
                }
            } catch (e: HttpException) {
                _loginLiveData.postValue(Resource.Error(Throwable("HTTP error: ${e.message()}")))
                _responseMessage.postValue(e.message().toString())
            } catch (e: IOException) {
                _loginLiveData.postValue(Resource.Error(Throwable("Network error: ${e.message}")))
                _responseMessage.postValue(e.message.toString())
            } catch (e: Exception) {
                _loginLiveData.postValue(Resource.Error(Throwable(e.message.toString())))
                _responseMessage.postValue(e.message.toString())
            }
        }
    }

    /**
     * Helper method to extract the error message from the errorResponse JSON
     */
    private fun extractErrorMessage(errorResponse: String?): String? {
        return try {
            val json = errorResponse?.let { JSONObject(it) }
            json?.getString("message")
        } catch (e: JSONException) {
            null
        }
    }
}