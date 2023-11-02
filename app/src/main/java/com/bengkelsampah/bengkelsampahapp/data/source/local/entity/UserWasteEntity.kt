package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KeranjangKu")
data class UserWasteEntity(
    @PrimaryKey
    @ColumnInfo("wasteId")
    val wasteId: Int,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("amout")
    val amount: Double,
)
