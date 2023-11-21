package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.MoneybagHistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserBankSampahModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.BankSampahRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class BankSampahRepositoryImpl @Inject constructor() : BankSampahRepository {
    override val userBankSampahData: Flow<UserBankSampahModel> = flow {
        delay(2000)
        emit(UserBankSampahModel.dummyData)
    }

    override fun getUserBankHistory(): Flow<List<MoneybagHistoryModel>> = flow {
        delay(2000)
        emit(MoneybagHistoryModel.dummyData)
    }

}