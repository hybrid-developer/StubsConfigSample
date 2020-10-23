package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("colours") val colours: Colours,
    @SerializedName("font") val font: Font,
    @SerializedName("logoUrl") val logoUrl: String
)