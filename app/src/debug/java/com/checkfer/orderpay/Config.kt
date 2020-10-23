package com.checkfer.orderpay

import com.checkfer.core.network.api.OrderPayApiConfig
import com.checkfer.orderpay.di.ApplicationComponent
import com.checkfer.orderpay.di.ConfigContract
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.logging.HttpLoggingInterceptor

object Config : ConfigContract {

    private val interceptors by lazy {
        listOf(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
            StethoInterceptor()
        )
    }

    override fun orderPayApiConfig() = OrderPayApiConfig(interceptors)

    override fun applicationInjector(application: OrderPayApplication): ApplicationComponent {
        Stetho.initializeWithDefaults(application)
        return super.applicationInjector(application)
    }
}
