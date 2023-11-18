package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
)