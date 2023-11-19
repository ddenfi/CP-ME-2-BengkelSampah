package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import java.math.BigDecimal

@Entity(tableName = "WasteBox")
data class WasteBoxEntity(
    @PrimaryKey
    @ColumnInfo("wasteId")
    val wasteId: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("amount")
    val amount: Double,

    @ColumnInfo("unit")
    val unit: String,

    @ColumnInfo("price")
    val pricePerUnit: Int,

    @ColumnInfo("waste_type")
    val wasteType: String
)

fun WasteBoxEntity.asExternalLayer() = WasteBoxModel(
    waste = WasteModel(
        wasteId = wasteId,
        name = name,
        unit = unit,
        pricePerUnit = pricePerUnit,
        wasteType = wasteType
    ), amount = amount
)