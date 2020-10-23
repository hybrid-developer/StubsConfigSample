package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address") val address: Address,
    @SerializedName("beacons") val beacons: Beacons,
    @SerializedName("geoFence") val geoFence: GeoFence
)