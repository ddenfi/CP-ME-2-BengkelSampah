package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteSoldModel
import kotlinx.coroutines.flow.Flow

interface WasteBoxRepository {
    fun getWasteBoxItems(): Flow<List<WasteSoldModel>>
}