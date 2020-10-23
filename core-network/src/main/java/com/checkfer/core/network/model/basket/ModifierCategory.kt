package com.checkfer.core.network.model.basket

import com.google.gson.annotations.SerializedName

data class ModifierCategory(
    @SerializedName("id") val id: String,
    @SerializedName("modifierId") val modifierId: String,
    @SerializedName("modifiers") val modifiers: List<Modifier>,
    @SerializedName("price") val price: Int,
    @SerializedName("quantity") val quantity: Int
)