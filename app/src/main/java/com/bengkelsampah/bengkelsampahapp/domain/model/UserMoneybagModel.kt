package com.bengkelsampah.bengkelsampahapp.domain.model

data class UserMoneybagModel(
    val userId: String,
    val name: String,
    val balance: Int
) {
    companion object {
        val dummyData =
            UserMoneybagModel(userId = "ID-0001", name = "Lesti Kejora", balance = 25000)
    }
}