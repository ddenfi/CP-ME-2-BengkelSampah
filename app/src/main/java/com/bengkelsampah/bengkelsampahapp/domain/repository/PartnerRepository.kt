package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import kotlinx.coroutines.flow.Flow

interface PartnerRepository {
    fun getPartners(): Flow<GetPartner>

    fun getPartnersById(partnerId: String): Flow<PartnerById>
}