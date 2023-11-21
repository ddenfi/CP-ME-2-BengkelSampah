package com.bengkelsampah.bengkelsampahapp.ui.banksampah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.BankSampahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BankSampahViewModel @Inject constructor(
    private val bankSampahRepository: BankSampahRepository
) : ViewModel() {
    val userBankSampahData = bankSampahRepository.userBankSampahData.asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), Resource.Loading()
    )

    fun getUserBankSampahHistory() = bankSampahRepository.getUserBankHistory().asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), Resource.Loading()
    )

}