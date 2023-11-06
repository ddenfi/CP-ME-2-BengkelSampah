package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData

sealed interface HistoryUiState {
    data class Success(val history: List<DummyHistoryData>) : HistoryUiState

    data class Error(val message: String? = null) : HistoryUiState

    object Loading : HistoryUiState
}