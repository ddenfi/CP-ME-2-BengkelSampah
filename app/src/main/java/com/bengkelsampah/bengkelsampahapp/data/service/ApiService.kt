package com.bengkelsampah.bengkelsampahapp.data.service

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartnerItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ApiService {
    @GET("partners")
    suspend fun getPartner(): GetPartner

    @GET("partners/{partnerid}")
    suspend fun getPartnerById(
        @Path("partnerid") partnerId: UUID
    ) : GetPartnerItem


}