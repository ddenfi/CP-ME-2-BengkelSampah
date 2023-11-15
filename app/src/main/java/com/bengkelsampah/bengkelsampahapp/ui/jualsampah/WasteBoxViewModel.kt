package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import androidx.lifecycle.ViewModel
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WasteBoxViewModel @Inject constructor(
    private val wasteBoxRepository: WasteBoxRepository
) : ViewModel() {
    fun getWasteBoxItem(): Flow<WasteBoxUiState> =
        wasteBoxRepository.getWasteBoxItems().asResource().map { resourceWasteBox ->
            when (resourceWasteBox) {
                is Resource.Success -> WasteBoxUiState.Success(resourceWasteBox.data)
                is Resource.Loading -> WasteBoxUiState.Loading
                is Resource.Error -> WasteBoxUiState.Error(resourceWasteBox.exception?.message)
            }
        }
}