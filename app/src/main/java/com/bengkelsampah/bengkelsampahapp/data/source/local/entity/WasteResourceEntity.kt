package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit

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

fun WasteResourceEntity.asExternalModel() = WasteModel(
    wasteId = wasteId,
    name = name,
    unit = WasteUnit.valueOf(unit),
    pricePerUnit = pricePerUnit,
    wasteType = wasteType
)
