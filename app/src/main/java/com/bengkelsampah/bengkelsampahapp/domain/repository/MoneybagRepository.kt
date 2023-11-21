package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.MoneybagHistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserMoneybagModel
import kotlinx.coroutines.flow.Flow

interface MoneybagRepository {

    val userMoneybag: Flow<UserMoneybagModel>

    fun getUserMoneybagHistory():Flow<List<MoneybagHistoryModel>>
}