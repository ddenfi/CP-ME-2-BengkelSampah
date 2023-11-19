package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity

data class WasteBoxModel(
    val waste: WasteModel,
    val amount: Double
) {
    companion object {
        val dummyData = listOf(
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Buku",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1000,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Karton",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1200,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Tembaga Kabel",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 5000,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Kaleng",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1500,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            )
        )

        fun countSubtotal(wastePricePerUnit: Int, wasteAmount: Double) =
            wastePricePerUnit * wasteAmount
    }
}

fun WasteBoxModel.asWasteBoxEntity() =
    WasteBoxEntity(
        wasteId = waste.wasteId,
        name = waste.name,
        amount = amount,
        unit = waste.unit,
        pricePerUnit = waste.pricePerUnit,
        wasteType = waste.wasteType
    )
