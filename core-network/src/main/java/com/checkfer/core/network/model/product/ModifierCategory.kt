package com.checkfer.core.network.model.product

import com.google.gson.annotations.SerializedName

data class ModifierCategory(
    @SerializedName("choice") val choice: String,
    @SerializedName("id") val id: String,
    @SerializedName("modifiers") val modifiers: List<Modifier>,
    @SerializedName("overridesProductPrice") val overridesProductPrice: Boolean,
    @SerializedName("title") val title: String
)