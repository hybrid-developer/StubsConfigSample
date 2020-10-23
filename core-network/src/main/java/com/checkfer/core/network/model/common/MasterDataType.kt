package com.checkfer.core.network.model.common

import com.google.gson.annotations.SerializedName

data class MasterDataType(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("selected") var selected: Boolean = false
)