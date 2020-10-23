package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class GeoFence(
    @SerializedName("point") val point: Point,
    @SerializedName("radius") val radius: Int
)