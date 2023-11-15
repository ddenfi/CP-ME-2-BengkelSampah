package com.bengkelsampah.bengkelsampahapp.ui.history

import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel

sealed interface HistoryDetailUiState {
    data class Success(val history: HistoryModel) : HistoryDetailUiState

    data class Error(val message: String? = null) : HistoryDetailUiState

    object Loading : HistoryDetailUiState
}