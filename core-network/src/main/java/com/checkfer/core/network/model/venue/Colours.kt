package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Colours(
    @SerializedName("actLblPrimary") val actLblPrimary: String,
    @SerializedName("actLblSecondary") val actLblSecondary: String,
    @SerializedName("actPrimary") val actPrimary: String,
    @SerializedName("actSecondary") val actSecondary: String,
    @SerializedName("bgPrimary") val bgPrimary: String,
    @SerializedName("bgSecondary") val bgSecondary: String,
    @SerializedName("lblPrimary") val lblPrimary: String,
    @SerializedName("lblSecondary") val lblSecondary: String
)