package com.bengkelsampah.bengkelsampahapp.domain.model

data class WasteModel(
    val id: String,
    val name: String,
    val amount: Double,
    val unit: WasteUnit,
    val pricePerUnit: Int
) {
    companion object {
        val dummyData = listOf(
            WasteModel(
                id = "WS-1",
                name = "Karton",
                amount = 3.0,
                unit = WasteUnit.KG,
                pricePerUnit = 1000
            ),
            WasteModel(
                id = "WS-2",
                name = "Besi Padat",
                amount = 3.0,
                unit = WasteUnit.KG,
                pricePerUnit = 4379
            ),
            WasteModel(
                id = "WS-13",
                name = "Hvs sam-sam",
                amount = 3.0,
                unit = WasteUnit.KG,
                pricePerUnit = 1200
            )
        )
    }
}
