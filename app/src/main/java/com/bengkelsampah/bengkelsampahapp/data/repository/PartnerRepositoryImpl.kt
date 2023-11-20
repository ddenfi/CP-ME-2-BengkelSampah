package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import com.bengkelsampah.bengkelsampahapp.domain.model.PartnerObject
import com.bengkelsampah.bengkelsampahapp.domain.repository.PartnerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PartnerRepositoryImpl @Inject constructor() : PartnerRepository {
    override fun getPartners(): Flow<GetPartner> = flow {
        delay(2000)
        emit(PartnerObject.partnerDummyData)
    }

    override fun getPartnersById(partnerId: String): Flow<PartnerById> = flow {
        delay(2000)
        emit(PartnerObject.partnerByIdDummyData)
    }
}