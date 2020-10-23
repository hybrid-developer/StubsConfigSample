package com.checkfer.core.network.api

import com.checkfer.core.network.model.common.MasterData
import retrofit2.http.GET

interface OrderPayApi {

    @GET("/preferences")
    suspend fun getMasterData(): MasterData
}