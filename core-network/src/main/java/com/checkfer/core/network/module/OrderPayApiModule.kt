package com.checkfer.core.network.module

import android.content.Context
import com.checkfer.core.network.NetworkState
import com.checkfer.core.network.api.ApiModule
import com.checkfer.core.network.api.OrderPayApiConfig
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class OrderPayApiModule(
    private val orderPayApiConfig: OrderPayApiConfig,
    private val context: Context,
    private val networkState: NetworkState
) {
    @Provides
    fun orderPayApiConfig() = orderPayApiConfig

    @Provides
    fun getContext() = context

    @Provides
    fun networkInfo(): NetworkState = networkState
}