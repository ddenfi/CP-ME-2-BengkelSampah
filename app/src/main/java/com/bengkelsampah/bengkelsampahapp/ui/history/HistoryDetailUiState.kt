package com.bengkelsampah.bengkelsampahapp.ui.history

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

sealed interface HistoryDetailUiState {
    data class Success(val history: WasteOrderModel) : HistoryDetailUiState

    data class Error(val message: String? = null) : HistoryDetailUiState

    object Loading : HistoryDetailUiState
}