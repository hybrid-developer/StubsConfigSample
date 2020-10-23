package com.checkfer.core.network.api

import android.content.Context
import com.checkfer.core.network.NetworkState
import com.checkfer.core.network.interceptors.AuthorisationInterceptor
import com.checkfer.core.network.interceptors.CacheInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @JvmStatic
    @Provides
    @OrderPayNetworkQualifier
    fun okHttpClient(
        apiConfig: OrderPayApiConfig,
        builder: OkHttpClient.Builder,
        networkState: NetworkState,
        context: Context
    ): OkHttpClient = builder
        .apply {
            cache(Cache(context.cacheDir, CACHE_SIZE)) // TODO Think about a new caching strategy
            addInterceptor(CacheInterceptor(networkState))
            addInterceptor(AuthorisationInterceptor())
            apiConfig.httpInterceptors.forEach { addNetworkInterceptor(it) }
        }
        .build()

    @JvmStatic
    @Provides
    @OrderPayNetworkQualifier
    fun retrofit(
        @OrderPayNetworkQualifier okHttpClient: OkHttpClient,
        builder: Retrofit.Builder
    ): Retrofit = builder.baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @JvmStatic
    @Provides
    fun orderPayFeedApi(@OrderPayNetworkQualifier retrofit: Retrofit): OrderPayApi = retrofit.create(
        OrderPayApi::class.java)

    private const val BASE_URL = "http://aea250665703f11eab2e1061663a1fe0-1592831467.eu-west-1.elb.amazonaws.com/api/1" // TODO move this to a config property file
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
}
