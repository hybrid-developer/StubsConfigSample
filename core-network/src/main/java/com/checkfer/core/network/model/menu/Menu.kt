package com.checkfer.core.network.model.menu

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("categories") val categories: List<Category>,
    @SerializedName("id") val id: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("title") val title: String
)