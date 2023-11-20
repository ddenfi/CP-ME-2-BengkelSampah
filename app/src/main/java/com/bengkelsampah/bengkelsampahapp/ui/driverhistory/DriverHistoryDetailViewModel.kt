package com.bengkelsampah.bengkelsampahapp.ui.driverhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DriverHistoryDetailViewModel @Inject constructor(
    private val driverHistoryRepository: DriverHistoryRepository
) : ViewModel() {
    fun getHistoryById(id: String) =
        driverHistoryRepository.getOrderHistoryById(id).asResource().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), Resource.Loading()
        )
}