package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById

sealed interface FormOrderUIState {
    data class Success(val partnerById: PartnerById) : FormOrderUIState

    data class Error(val message: String? = null) : FormOrderUIState

    object Loading : FormOrderUIState
}