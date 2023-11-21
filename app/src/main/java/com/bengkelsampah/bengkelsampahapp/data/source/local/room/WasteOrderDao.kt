package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WasteOrderDao {
    @Query("SELECT * FROM OfflineWasteOrder")
    fun getAllOrder(): Flow<List<WasteOrderEntity>>

    @Query("SELECT * FROM OfflineWasteOrder WHERE orderId = :ids")
    fun getOrderById(ids: String): Flow<WasteOrderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = WasteOrderEntity::class)
    fun insertOrders(userWasteOrder: List<WasteOrderEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = WasteOrderEntity::class)
    fun insertOrder(order: WasteOrderEntity)

    @Upsert
    suspend fun upsertOrder(userWasteOrder: WasteOrderEntity)
}