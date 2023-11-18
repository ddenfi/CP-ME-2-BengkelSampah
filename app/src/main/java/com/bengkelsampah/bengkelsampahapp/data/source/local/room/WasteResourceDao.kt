package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WasteResourceDao {
    @Query("SELECT * FROM WasteResource")
    fun getAllWasteResources(): Flow<List<WasteResourceEntity>>

    @Query("SELECT * FROM WasteResource WHERE wasteId = :ids")
    fun getWasteById(ids: String): Flow<WasteResourceEntity>
}