package com.bengkelsampah.bengkelsampahapp.domain.model

data class UserPreferencesDataModel(
    val isLogin: Boolean,
    val shouldShowOnboard:Boolean,
    val userRole: UserRole
)
