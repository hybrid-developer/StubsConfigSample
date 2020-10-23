package com.checkfer.core.network.api

import okhttp3.Interceptor

class OrderPayApiConfig(
    val httpInterceptors: List<Interceptor> = emptyList()
)