package com.checkfer.core.network.model.common

import com.google.gson.annotations.SerializedName

data class MasterData(
    @SerializedName("cuisines") var cuisines: List<MasterDataType>? = null,
    @SerializedName("allergens") var allergens: List<MasterDataType>? = null,
    @SerializedName("dietary") var dietary: List<MasterDataType?>? = null,
    @SerializedName("notifications") var notifications: List<MasterDataType?>? = null,
    @SerializedName("marketing") var marketing: List<MasterDataType?>? = null,
    @SerializedName("beacons") var beacons: List<MasterDataType?>? = null,
    @SerializedName("cuisinesMap") var cuisinesMap: Map<String, String>? = null,
    @SerializedName("allergensMap") var allergensMap: Map<String, String>? = null,
    @SerializedName("dietaryMap") var dietaryMap: Map<String, String>? = null
)