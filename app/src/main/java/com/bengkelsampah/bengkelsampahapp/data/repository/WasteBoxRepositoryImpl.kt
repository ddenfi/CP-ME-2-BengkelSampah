package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteResourceEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalLayer
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalModel
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteBoxDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteResourceDao
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.asWasteBoxEntity
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class WasteBoxRepositoryImpl @Inject constructor(
    private val wasteResourceDao: WasteResourceDao,
    private val wasteBoxDao: WasteBoxDao
) : WasteBoxRepository {
    override fun getWasteBoxItems(): Flow<List<WasteBoxModel>> =
        wasteBoxDao.getUserWastes()
            .onStart { delay(2000) }
            .map { it.map(WasteBoxEntity::asExternalLayer) }

    override fun getAllWastes(): Flow<List<WasteModel>> =
        wasteResourceDao.getAllWasteResources()
            .onStart { delay(2000) }
            .map { it.map(WasteResourceEntity::asExternalModel) }

    override fun getWasteBoxItemById(id: String): Flow<WasteBoxModel?> =
        wasteBoxDao.getWasteBoxItemById(id).map { it?.asExternalLayer() }

    override fun addToWasteBox(waste: WasteBoxModel) {
        CoroutineScope(Dispatchers.IO).launch {
            wasteBoxDao.insertUserWaste(
                WasteBoxEntity(
                    wasteId = waste.waste.wasteId,
                    name = waste.waste.name,
                    amount = waste.amount,
                    unit = waste.waste.unit.name,
                    pricePerUnit = waste.waste.pricePerUnit,
                    wasteType = waste.waste.wasteType
                )
            )
        }
    }

    override fun searchWaste(query: String): Flow<List<WasteModel>> =
        wasteResourceDao.searchWaste("%${query}%")
            .onStart { delay(1000) }
            .map { it.map(WasteResourceEntity::asExternalModel) }

    override fun deleteFromWasteBox(waste: WasteBoxModel) {
        CoroutineScope(Dispatchers.IO).launch {
            wasteBoxDao.deleteUserWaste(waste.asWasteBoxEntity())
        }
    }

    override fun countWasteBoxItems(): Flow<Int> = wasteBoxDao.countWasteBoxItems()

    override fun updateWasteBoxItem(waste: WasteBoxModel) {
        CoroutineScope(Dispatchers.IO).launch {
            wasteBoxDao.updateUserWaste(waste.asWasteBoxEntity())
        }
    }
}