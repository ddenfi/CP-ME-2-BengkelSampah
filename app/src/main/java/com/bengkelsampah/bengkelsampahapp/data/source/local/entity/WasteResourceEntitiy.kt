package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit

data class WasteResourceEntitiy(
    val id:String,
    val name: String,
    val unit: String,
    val pricePerUnit: Int
)
