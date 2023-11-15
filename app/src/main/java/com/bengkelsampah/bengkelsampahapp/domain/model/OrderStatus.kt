package com.bengkelsampah.bengkelsampahapp.domain.model

enum class OrderStatus(val statusName: String) {
    DONE("Done"),
    PICKING_UP("Picking Up"),
    WAIT_CONFIRMATION("Wait Confirmation"),
    PROCESSED("Processed")
}