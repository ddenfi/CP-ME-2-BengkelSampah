package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner

sealed interface PartnerUiState {
    data class Success(val partners: GetPartner) : PartnerUiState

    data class Error(val message: String? = null) : PartnerUiState

    object Loading : PartnerUiState
}