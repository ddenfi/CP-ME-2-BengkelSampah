package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

sealed interface HistoryUiState {
    data class Success(val history: List<WasteOrderModel>) : HistoryUiState

    data class Error(val message: String? = null) : HistoryUiState

    object Loading : HistoryUiState
}