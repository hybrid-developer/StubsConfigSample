package com.checkfer.core.network.model.product

import com.checkfer.core.network.model.common.Price
import com.google.gson.annotations.SerializedName

data class Modifier(
    @SerializedName("choice") val choice: String,
    @SerializedName("id") val id: String,
    @SerializedName("imageUrl") val imageUrl: Any?,
    @SerializedName("options") val options: List<Option>,
    @SerializedName("overridesProductPrice") val overridesProductPrice: Boolean,
    @SerializedName("price") val price: Price,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("title") val title: String,
    @SerializedName("whiteListedModifierCategoryIds") val whiteListedModifierCategoryIds: List<Any>
)