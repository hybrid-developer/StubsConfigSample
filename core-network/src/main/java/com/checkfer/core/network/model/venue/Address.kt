package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("line1") val line1: String,
    @SerializedName("line2") val line2: String,
    @SerializedName("postcode") val postcode: String
)