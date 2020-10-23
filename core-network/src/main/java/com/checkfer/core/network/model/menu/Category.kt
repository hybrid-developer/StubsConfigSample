package com.checkfer.core.network.model.menu

import com.checkfer.core.network.model.common.Product
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("products") val products: List<Product>,
    @SerializedName("title") val title: String
)