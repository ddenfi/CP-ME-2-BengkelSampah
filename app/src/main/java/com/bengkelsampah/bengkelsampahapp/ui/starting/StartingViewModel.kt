package com.bengkelsampah.bengkelsampahapp.ui.starting

import androidx.lifecycle.ViewModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUserPreferences() = userRepository.userPreferencesData
}