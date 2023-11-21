package com.bengkelsampah.bengkelsampahapp.data.source.remote.response.auth

import com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth.LoginData

data class LoginResponse(
    val message: String,
    val accessToken: String,
    val refreshToken: String,
    val data: LoginData
)