package com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)

data class LoginData(
    val userID: String,
    val phoneNumber: String,
    val name: String,
    val address: String?,
    val role: String,
    val email: String?
)