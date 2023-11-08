package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.PreferenceDataStore
import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserPreferencesDataModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

class UserRepositoryImpl @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) : UserRepository {
    /**
     * Get data from dummy due API still in development
     */
    override val userData: Flow<UserDataModel> = flow {
        delay(2000)
        emit(UserDataModel.dummyData)
    }

    override val userPreferencesData: Flow<UserPreferencesDataModel> =
        preferenceDataStore.userPreferencesData

    override fun updateUser(data: UserDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
        preferenceDataStore.setLoginStatus(isLogin)
    }

    override suspend fun setShouldShowOnboard(shouldShowOnboard: Boolean) {
        preferenceDataStore.setShouldShowOnboard(shouldShowOnboard)
    }
}