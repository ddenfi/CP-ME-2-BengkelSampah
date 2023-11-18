package com.bengkelsampah.bengkelsampahapp.data.source.remote.network

import com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth.LoginRequest
import com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth.RegisterRequest
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.auth.LoginResponse
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.auth.RegisterResponse
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ApiService {
    @POST("auth/register")
    suspend fun postRegister(
        @Body registerRequest: RegisterRequest
    ) : Response<RegisterResponse>

    @POST("auth/login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @GET("partners")
    suspend fun getPartner(): GetPartner

    @GET("partners/{partnerid}")
    suspend fun getPartnerById(
        @Path("partnerid") partnerId: UUID
    ): PartnerById
}