package com.bengkelsampah.bengkelsampahapp.data.source.remote.request.auth

data class RegisterRequest(
    val name: String,
    val phoneNumber: String,
    val password: String
)

data class RegisterData(
    val userID: String,
    val name: String,
    val phoneNumber: String,
    val address: String?,
    val email: String?,
    val createdAt: String,
    val customerID: String,
    val balance: Double
)