package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteResourceEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalLayer
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalModel
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteOrderDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteResourceDao
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.model.asWasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PickupWasteRepositoryImpl @Inject constructor(
    private val wasteOrderDao: WasteOrderDao,
    private val wasteResourceDao: WasteResourceDao
) : PickupWasteRepository {
    /**
     * Get all WasteOrder from local database and filter it out only for [OrderStatus.PROCESSED] and [OrderStatus.PICKING_UP]
     * Purpose [delay] on flow is to simulate call API
     */
    override fun getAllOrders(): Flow<List<WasteOrderModel>> =
        wasteOrderDao.getAllOrder().onStart { delay(2000) }
            .map { it.map(WasteOrderEntity::asExternalModel) }
            .map { it.filter { it.status == OrderStatus.PROCESSED || it.status == OrderStatus.PICKING_UP } }

    /**
     * Search  WasteOrder from local database
     * Purpose [delay] on flow is to simulate call API
     */
    override fun getOrderById(ids: String): Flow<WasteOrderModel> =
        wasteOrderDao.getOrderById(ids).onStart { delay(2000) }.map { it.asExternalModel() }

    /**
     * Get all populated waste from local database
     */
    override fun getAllWaste(): Flow<List<WasteModel>> =
        wasteResourceDao.getAllWasteResources().map { it.map(WasteResourceEntity::asExternalModel) }

    /**
     * Search waste from populated waste local database
     * @param query use asterisk to include search in the middle of words
     */
    override fun searchWaste(query: String): Flow<List<WasteModel>> =
        wasteResourceDao.searchWaste("*${query}*")
            .map { it.map(WasteResourceEntity::asExternalModel) }

    /**
     * Update waste order to local database
     */
    override suspend fun updateOrder(wasteOrder: WasteOrderModel) =
        wasteOrderDao.upsertOrder(wasteOrder.asWasteOrderEntity())

}