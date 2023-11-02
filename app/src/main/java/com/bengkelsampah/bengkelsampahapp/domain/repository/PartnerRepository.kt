package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.data.source.remote.network.ApiService
import java.util.UUID

class PartnerRepository (
    private val apiService: ApiService
) {
    suspend fun getPartner() = apiService.getPartner()
    suspend fun getPartnerById(partnerId: UUID) = apiService.getPartnerById(partnerId)
}