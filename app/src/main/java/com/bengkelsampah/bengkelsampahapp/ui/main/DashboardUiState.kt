package com.bengkelsampah.bengkelsampahapp.ui.main

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

sealed interface DashboardUiState {
    data class Success(val user: UserDataModel,val activeOrder:List<WasteOrderModel>) :
        DashboardUiState

    data class Error(val message: String? = null) : DashboardUiState

    object Loading : DashboardUiState
}