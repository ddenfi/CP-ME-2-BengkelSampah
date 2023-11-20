package com.bengkelsampah.bengkelsampahapp.ui.moneybag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.MoneybagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoneybagViewModel @Inject constructor(
     private val moneybagRepository: MoneybagRepository
) : ViewModel() {

    val userMoneyBagData = moneybagRepository.userMoneybag.asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), Resource.Loading()
    )

    fun getUseMoneybagHistory() = moneybagRepository.getUserMoneybagHistory().asResource()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Resource.Loading())

}