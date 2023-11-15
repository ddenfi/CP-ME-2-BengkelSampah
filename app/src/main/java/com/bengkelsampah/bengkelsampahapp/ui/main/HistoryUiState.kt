package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel

sealed interface HistoryUiState {
    data class Success(val history: List<HistoryModel>) : HistoryUiState

    data class Error(val message: String? = null) : HistoryUiState

    object Loading : HistoryUiState
}