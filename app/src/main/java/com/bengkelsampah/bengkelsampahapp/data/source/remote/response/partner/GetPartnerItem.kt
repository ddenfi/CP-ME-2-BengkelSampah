package com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetPartnerItem(
    @SerializedName("result")
    val partnerId: UUID,
    @SerializedName("statusCode")
    val address: String,
    @SerializedName("result")
    val phoneNumber: String,
    @SerializedName("name")
    val name: String
)
