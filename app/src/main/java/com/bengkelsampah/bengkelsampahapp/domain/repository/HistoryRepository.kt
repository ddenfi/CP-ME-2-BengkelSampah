package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getActiveTransaction(): Flow<List<HistoryModel>>

    fun getHistoryById(id: Int): Flow<HistoryModel>
}