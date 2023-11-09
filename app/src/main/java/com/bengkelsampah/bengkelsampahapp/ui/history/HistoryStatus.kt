package com.bengkelsampah.bengkelsampahapp.ui.history

enum class HistoryStatus(val statusValue: String, val color: String) {
    MENUNGGU_KONFIRMASI("Menunggu Konfirmasi", "#999797"),
    DIPROSES("Diproses", "#FFAB2A"),
    SELESAI("Selesai", "#519B37"),
    DIBATALKAN("Dibatalkan", "#E62E2E")
}