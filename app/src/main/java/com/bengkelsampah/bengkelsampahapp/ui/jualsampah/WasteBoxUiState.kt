package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel

sealed interface WasteBoxUiState {
    data class Success(val wasteBoxItems: List<WasteBoxModel>) : WasteBoxUiState

    data class Error(val message: String? = null) : WasteBoxUiState

    object Loading : WasteBoxUiState
}