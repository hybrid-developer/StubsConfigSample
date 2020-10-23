package com.checkfer.core.network.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatarUrl") val avatarUrl: Any?,
    @SerializedName("email") val email: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("id") val id: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("phone") val phone: String
)