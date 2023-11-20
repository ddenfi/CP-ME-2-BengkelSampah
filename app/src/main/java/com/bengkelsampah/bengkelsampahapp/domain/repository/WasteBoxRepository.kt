package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import kotlinx.coroutines.flow.Flow

interface WasteBoxRepository {
    fun getWasteBoxItems(): Flow<List<WasteBoxModel>>

    fun getAllWastes(): Flow<List<WasteModel>>

    fun addToWasteBox(waste: WasteBoxModel)
}