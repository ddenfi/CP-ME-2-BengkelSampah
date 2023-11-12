package com.bengkelsampah.bengkelsampahapp.domain.model

data class WasteModel(
    val id:String,
    val name: String,
    val amount: Double,
    val unit: WasteUnit,
    val pricePerUnit: Int
)
