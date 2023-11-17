package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel

sealed interface DashboardUiState {
    data class Success(val user: UserDataModel, val activeTransaction: List<HistoryModel>) :
        DashboardUiState

    data class Error(val message: String? = null) : DashboardUiState

    object Loading : DashboardUiState
}