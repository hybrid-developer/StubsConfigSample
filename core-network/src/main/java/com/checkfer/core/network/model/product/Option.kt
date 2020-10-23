package com.checkfer.core.network.model.product

import com.checkfer.core.network.model.common.Price
import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("id") val id: String,
    @SerializedName("price") val price: Price,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("title") val title: String
)