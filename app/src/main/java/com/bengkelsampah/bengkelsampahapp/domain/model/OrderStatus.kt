package com.bengkelsampah.bengkelsampahapp.domain.model

enum class OrderStatus(val statusName: String, val color: String) {
    DONE("Done", "#519B37"),
    PICKING_UP("Picking Up", "#FFAB2A"),
    WAIT_CONFIRMATION("Wait Confirmation", "#999797"),
    PROCESSED("Processed", "#FFAB2A"),
    CANCELLED("Cancelled", "#E62E2E")
}