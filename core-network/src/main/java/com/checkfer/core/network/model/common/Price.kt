package com.checkfer.core.network.model.common

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("currencyCode") val currencyCode: String,
    @SerializedName("units") val units: Double
)