package com.bengkelsampah.bengkelsampahapp.data.source.remote.network

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface ApiService {
    @GET("partners")
    suspend fun getPartner(): GetPartner

    @GET("partners/{partnerid}")
    suspend fun getPartnerById(
        @Path("partnerid") partnerId: UUID
    ): PartnerById
}