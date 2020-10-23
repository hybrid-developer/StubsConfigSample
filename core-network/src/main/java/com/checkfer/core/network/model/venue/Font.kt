package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Font(
    @SerializedName("family") val family: String,
    @SerializedName("sizes") val sizes: Sizes
)