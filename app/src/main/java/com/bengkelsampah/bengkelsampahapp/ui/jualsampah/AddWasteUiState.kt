package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel

sealed interface AddWasteUiState {
    data class Success(val wasteType: List<WasteModel>) : AddWasteUiState

    data class Error(val message: String? = null) : AddWasteUiState

    object Loading : AddWasteUiState
}