package com.bengkelsampah.bengkelsampahapp.domain.model

data class WasteSoldModel(
    val waste: WasteModel,
    val amount: Int
) {
    companion object {
        val cartDummyData = listOf(
            WasteSoldModel(
                WasteModel(
                    name = "Buku",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1000,
                ),
                amount = 2,
            ),
            WasteSoldModel(
                WasteModel(
                    name = "Karton",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1200,
                ),
                amount = 2,
            ),
            WasteSoldModel(
                WasteModel(
                    name = "Tembaga Kabel",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 5000,
                ),
                amount = 2,
            ),
            WasteSoldModel(
                WasteModel(
                    name = "Kaleng",
                    unit = WasteUnit.KG.abbreviation,
                    pricePerUnit = 1500,
                ),
                amount = 2,
            )
        )

        fun countSubtotal(wastePricePerUnit: Int, wasteAmount: Int) =
            wastePricePerUnit * wasteAmount
    }
}
