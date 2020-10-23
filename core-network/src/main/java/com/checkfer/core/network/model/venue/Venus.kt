package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Venus(
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("venues") val venues: List<Venue>
)