package com.bengkelsampah.bengkelsampahapp.domain.model

data class WasteModel(
    val name: String,
    val unit: String,
    val pricePerUnit: Int
) {
    companion object {
        val wasteDummyData = listOf(
            WasteModel(
                name = "Karton",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 1000
            ),
            WasteModel(
                name = "Besi Padat",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 4379
            ),
            WasteModel(
                name = "Minyak Jelantah",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 4000
            ),
            WasteModel(
                name = "ABK Aqua Botol Kotor",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 1000
            ),
            WasteModel(
                name = "Duplek",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 300
            ),
            WasteModel(
                name = "Kabin",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 2500
            ),
            WasteModel(
                name = "Buku",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 1000
            ),
            WasteModel(
                name = "Takar/Tempurung",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 300
            ),
            WasteModel(
                name = "AKI",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 8000
            ),
            WasteModel(
                name = "Alma Lembek (Panci)",
                unit = WasteUnit.KG.abbreviation,
                pricePerUnit = 16625
            ),
        )
    }
}
