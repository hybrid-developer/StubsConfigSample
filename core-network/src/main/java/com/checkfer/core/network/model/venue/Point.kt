package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lat") val lat: Double,
    @SerializedName("long") val long: Double
)