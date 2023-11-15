package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteSoldModel

sealed interface WasteBoxUiState {
    data class Success(val wasteBoxItems: List<WasteSoldModel>) : WasteBoxUiState

    data class Error(val message: String? = null) : WasteBoxUiState

    object Loading : WasteBoxUiState
}