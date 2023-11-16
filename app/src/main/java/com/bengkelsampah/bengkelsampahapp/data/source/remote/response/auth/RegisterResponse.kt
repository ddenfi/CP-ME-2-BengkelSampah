package com.bengkelsampah.bengkelsampahapp.data.source.remote.response.auth

import com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth.RegisterData

data class RegisterResponse(
    val message: String,
    val data: RegisterData
)