package com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner

import com.google.gson.annotations.SerializedName

data class PartnerById(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: GetPartnerItem
)
