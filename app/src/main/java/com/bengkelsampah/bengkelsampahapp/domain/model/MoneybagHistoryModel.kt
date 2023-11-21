package com.bengkelsampah.bengkelsampahapp.domain.model

data class MoneybagHistoryModel(
    val transactionId: String,
    val title: String,
    val description: String,
    val price: Int,
    val date: String
) {
    companion object {
        val dummyData = listOf(
            MoneybagHistoryModel(
                transactionId = "MB-001",
                title = "Pulsa Telkomsel 25000",
                description = "",
                price = 25000,
                date = "20 November 2023 13.00"
            ),
            MoneybagHistoryModel(
                transactionId = "MB-002",
                title = "Token Listrik 50000",
                description = "",
                price = 51000,
                date = "20 November 2023 13.00"
            ),
            MoneybagHistoryModel(
                transactionId = "MB-003",
                title = "Pulsa Three 25000",
                description = "",
                price = 25500,
                date = "20 November 2023 13.00"
            ),
            MoneybagHistoryModel(
                transactionId = "MB-004",
                title = "Kuota Telkomsel 5 GB",
                description = "",
                price = 25000,
                date = "20 November 2023 13.00"
            ),
            MoneybagHistoryModel(
                transactionId = "MB-005",
                title = "Voucher Belanja Indomaret",
                description = "",
                price = 50000,
                date = "20 November 2023 13.00"
            ),
        )
    }
}
