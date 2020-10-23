package com.checkfer.core.network.model.basket

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("errors") val errors: List<Any>,
    @SerializedName("id") val id: String,
    @SerializedName("modifierCategories") val modifierCategories: List<ModifierCategory>,
    @SerializedName("price") val price: Int,
    @SerializedName("productId") val productId: String,
    @SerializedName("quantity") val quantity: Int
)