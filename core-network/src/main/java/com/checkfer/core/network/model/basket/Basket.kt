package com.checkfer.core.network.model.basket

import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("errors") val errors: List<Any>,
    @SerializedName("id") val id: String,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("status") val status: String,
    @SerializedName("total") val total: String,
    @SerializedName("venueId") val venueId: String
)