package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import androidx.lifecycle.ViewModel
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.PartnerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PartnerViewModel @Inject constructor(
    private val partnerRepository: PartnerRepository
) : ViewModel() {
    fun getPartner(): Flow<PartnerUiState> =
        partnerRepository.getPartners().asResource().map { resourcePartner ->
            when (resourcePartner) {
                is Resource.Success -> PartnerUiState.Success(resourcePartner.data)
                is Resource.Loading -> PartnerUiState.Loading
                is Resource.Error -> PartnerUiState.Error(resourcePartner.exception?.message)
            }
        }
}