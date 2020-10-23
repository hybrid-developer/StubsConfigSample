package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Sizes(
    @SerializedName("h1") val h1: Int,
    @SerializedName("h2") val h2: Int,
    @SerializedName("h3") val h3: Int,
    @SerializedName("highlights") val highlights: Int,
    @SerializedName("smallText") val smallText: Int,
    @SerializedName("text") val text: Int
)