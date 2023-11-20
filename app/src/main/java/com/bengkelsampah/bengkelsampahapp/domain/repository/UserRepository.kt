package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserPreferencesDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * Get detail user information data from remote or local
     */
    val userData: Flow<UserDataModel>

    /**
     * Get user preferences data from data store
     */
    val userPreferencesData: Flow<UserPreferencesDataModel>

    /**
     * Update User data
     */
    fun updateUser(data: UserDataModel)

    /**
     * Set user login status to preferences data store
     */
    suspend fun setLoginStatus(isLogin: Boolean)

    /**
     * Set whether app should show onboarding to preferences data store
     */
    suspend fun setShouldShowOnboard(shouldShowOnboard: Boolean)

    /**
     * Set user role
     */
    suspend fun setUserRole(userRole: UserRole)

}