package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.MyBucketEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.util.WasteBoxConverter
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.flow.Flow

interface WasteBoxRepository {
    fun getWasteBoxItems(): Flow<List<WasteBoxModel>>
    fun getAllWastes(): Flow<List<WasteModel>>
    fun getWasteBoxItemById(id: String): Flow<WasteBoxModel?>
    fun addToWasteBox(waste: WasteBoxModel)
    fun searchWaste(query: String): Flow<List<WasteModel>>
    fun deleteFromWasteBox(waste: WasteBoxModel)
    fun countWasteBoxItems(): Flow<Int>
    fun updateWasteBoxItem(waste: WasteBoxModel)
    fun insertOrder(order: WasteOrderModel)
    fun clearWasteBox()
    fun addToWasteBucket(waste: WasteBoxModel)
    fun getWasteBucketItems(): Flow<List<WasteBoxModel>>
    fun deleteFromWasteBucket(waste: WasteBoxModel)
    fun updateWasteBucketItem(waste: WasteBoxModel)
}