package com.checkfer.core.network.model.common

import com.checkfer.core.network.model.product.ModifierCategory
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: Any?,
    @SerializedName("modifierCategories") val modifierCategories: List<ModifierCategory>,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Price,
    @SerializedName("quantity") val quantity: Int
)