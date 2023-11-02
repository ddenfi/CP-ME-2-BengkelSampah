package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl :UserRepository {
    /**
     * Get data from dummy due API still in development
     */
    override val userData: Flow<UserDataModel> = flow {
        delay(2000)
        emit(UserDataModel.dummyData)
    }

    override fun updateUser(data: UserDataModel) {
        TODO("Not yet implemented")
    }
}