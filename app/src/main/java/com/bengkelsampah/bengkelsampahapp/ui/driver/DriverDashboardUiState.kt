package com.bengkelsampah.bengkelsampahapp.ui.driver

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

sealed interface DriverDashboardUiState {
    data class Success(val user: UserDataModel,val activeOrder:List<WasteOrderModel>) :
        DriverDashboardUiState

    data class Error(val message: String? = null) : DriverDashboardUiState

    object Loading : DriverDashboardUiState
}