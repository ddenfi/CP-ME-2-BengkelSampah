package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.MoneybagHistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserBankSampahModel
import kotlinx.coroutines.flow.Flow

interface BankSampahRepository {

    val userBankSampahData:Flow<UserBankSampahModel>
    fun getUserBankHistory(): Flow<List<MoneybagHistoryModel>>
}