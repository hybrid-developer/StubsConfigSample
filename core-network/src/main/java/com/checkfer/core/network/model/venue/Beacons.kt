package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Beacons(
    @SerializedName("payLock") val payLock: Any?,
    @SerializedName("threshold") val threshold: Any?
)