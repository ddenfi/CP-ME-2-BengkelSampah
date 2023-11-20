package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteResourceEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalLayer
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalModel
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteBoxDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteResourceDao
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
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
}