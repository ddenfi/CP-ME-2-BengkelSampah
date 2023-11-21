package com.bengkelsampah.bengkelsampahapp.domain.model

import android.os.Parcelable
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class WasteBoxModel(
    val waste: WasteModel,
    val amount: Double
) : Parcelable {
    companion object {
        val dummyData = listOf(
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Buku",
                    unit = WasteUnit.KG,
                    pricePerUnit = 1000,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Karton",
                    unit = WasteUnit.KG,
                    pricePerUnit = 1200,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Tembaga Kabel",
                    unit = WasteUnit.KG,
                    pricePerUnit = 5000,
                    wasteType = "INORGANIC"
                ),
                amount = 2.0,
            ),
            WasteBoxModel(
                WasteModel(
                    wasteId = "WS-1",
                    name = "Kaleng",
                    unit = WasteUnit.KG,
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
        unit = waste.unit.name,
        pricePerUnit = waste.pricePerUnit,
        wasteType = waste.wasteType
    )
