package com.bengkelsampah.bengkelsampahapp.domain.model

data class WasteModel(
    val wasteId:String,
    val name: String,
    val unit: WasteUnit,
    val pricePerUnit: Int,
    val wasteType:String
) {
    companion object {
        val wasteDummyData = listOf(
            WasteModel(
                wasteId = "WS-1",
                name = "Karton",
                unit = WasteUnit.KG,
                pricePerUnit = 1000,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Besi Padat",
                unit = WasteUnit.KG,
                pricePerUnit = 4379,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Minyak Jelantah",
                unit = WasteUnit.KG,
                pricePerUnit = 4000,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "ABK Aqua Botol Kotor",
                unit = WasteUnit.KG,
                pricePerUnit = 1000,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Duplek",
                unit = WasteUnit.KG,
                pricePerUnit = 300,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Kabin",
                unit = WasteUnit.KG,
                pricePerUnit = 2500,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Buku",
                unit = WasteUnit.KG,
                pricePerUnit = 1000,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Takar/Tempurung",
                unit = WasteUnit.KG,
                pricePerUnit = 300,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "AKI",
                unit = WasteUnit.KG,
                pricePerUnit = 8000,
                wasteType = "INORGANIC"
            ),
            WasteModel(
                wasteId = "WS-1",
                name = "Alma Lembek (Panci)",
                unit = WasteUnit.KG,
                pricePerUnit = 16625,
                wasteType = "INORGANIC"
            ),
        )
    }
}
