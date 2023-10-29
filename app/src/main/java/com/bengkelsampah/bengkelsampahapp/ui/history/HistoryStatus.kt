package com.bengkelsampah.bengkelsampahapp.ui.history

enum class HistoryStatus(val statusValue: String, val color: String) {
    MENUNGGU_KONFIRMASI("Menunggu Konfirmasi", "#999797"),
    DIPROSES("Diproses", "#519B37"),
    SELESAI("Selesai", "#FFAB2A"),
    DIBATALKAN("Dibatalkan", "#E62E2E")
}