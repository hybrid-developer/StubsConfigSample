package com.checkfer.core.network.model.venue

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("pageSize") val pageSize: Int
)