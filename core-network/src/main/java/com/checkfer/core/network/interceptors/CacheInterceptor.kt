package com.checkfer.core.network.interceptors

import com.checkfer.core.network.NetworkState
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class CacheInterceptor @Inject constructor(
    private val networkState: NetworkState
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl: CacheControl
        var request = chain.request()
        val shouldCache = request.header(SHOULD_CACHE)

        if (shouldCache != null && shouldCache.equals(CACHE_DEFAULT_VALUE, ignoreCase = true)) {
            if (networkState.isNetworkConnected()) {
                cacheControl = CacheControl.Builder()
                    .maxAge(MAX_AGE_DAYS, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
            } else {
                cacheControl = CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(MAX_STALE_DAYS, TimeUnit.DAYS)
                        .build()
                request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
            }
        } else {
            cacheControl = CacheControl.Builder()
                .noStore()
                .noCache()
                .build()
            request = request.newBuilder()
                .cacheControl(cacheControl)
                .build()
        }
        return chain.proceed(request)
    }

    private companion object {
        private const val CACHE_DEFAULT_VALUE = "true"
        private const val MAX_AGE_DAYS = 10 // 10 Days
        private const val MAX_STALE_DAYS = 10 // 10 Days
        private const val SHOULD_CACHE = "should_cache"
    }
}