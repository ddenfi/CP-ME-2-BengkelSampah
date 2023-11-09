package com.bengkelsampah.bengkelsampahapp.ui.history

import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData

sealed interface HistoryDetailUiState {
    data class Success(val history: DummyHistoryData) : HistoryDetailUiState

    data class Error(val message: String? = null) : HistoryDetailUiState

    object Loading : HistoryDetailUiState
}