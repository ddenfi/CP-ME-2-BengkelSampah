package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository

data class UserDataModel(
    var userId: String,
    var email: String,
    var name: String,
    var givenName: String,
    var familyName: String,
    var nickname: String,
    var lastIp: String,
    var loginsCount: Int,
    var createdAt: String,
    var updatedAt: String,
    var lastLogin: String,
    var emailVerified: Boolean,
    var balance: Long
) {
    companion object {
        val dummyData = UserDataModel(
            userId = "unum",
            email = "cherie.hawkins@example.com",
            name = "Krystal Harper",
            givenName = "Ida Valencia",
            familyName = "Juliet Wilkins",
            nickname = "Kenny Romero",
            lastIp = "parturient",
            loginsCount = 3714,
            createdAt = "urna",
            updatedAt = "volumus",
            lastLogin = "volumus",
            emailVerified = false,
            balance = 10000
        )
    }
}
