package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WasteResource")
data class WasteResourceEntity(
    @PrimaryKey
    val wasteId:String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("unit")
    val unit: String,
    @ColumnInfo("price")
    val pricePerUnit: Int,
    @ColumnInfo("waste_type")
    val wasteType:String
)
