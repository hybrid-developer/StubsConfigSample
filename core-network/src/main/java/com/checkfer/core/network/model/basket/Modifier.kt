package com.checkfer.core.network.model.basket

import com.google.gson.annotations.SerializedName

data class Modifier(
    @SerializedName("allergiesAcceptedAt") val allergiesAcceptedAt: Any?,
    @SerializedName("basketItemId") val basketItemId: Any?,
    @SerializedName("id") val id: String,
    @SerializedName("modifierId") val modifierId: String,
    @SerializedName("parentId") val parentId: String,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int
)