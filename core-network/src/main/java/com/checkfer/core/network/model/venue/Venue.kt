package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("brand") val brand: Brand,
    @SerializedName("coverUrls") val coverUrls: List<String>,
    @SerializedName("description") val description: String,
    @SerializedName("distance") val distance: Double,
    @SerializedName("id") val id: String,
    @SerializedName("isOpen") val isOpen: Boolean,
    @SerializedName("location") val location: Location,
    @SerializedName("name") val name: String
)