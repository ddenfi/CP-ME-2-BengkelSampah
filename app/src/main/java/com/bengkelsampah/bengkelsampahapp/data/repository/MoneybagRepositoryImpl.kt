package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.MoneybagHistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserMoneybagModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.MoneybagRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoneybagRepositoryImpl @Inject constructor() : MoneybagRepository {

    override val userMoneybag: Flow<UserMoneybagModel> = flow {
        delay(2000)
        emit(UserMoneybagModel.dummyData)
    }

    override fun getUserMoneybagHistory(): Flow<List<MoneybagHistoryModel>> = flow {
        delay(2000)
        emit(MoneybagHistoryModel.dummyData)
    }

}