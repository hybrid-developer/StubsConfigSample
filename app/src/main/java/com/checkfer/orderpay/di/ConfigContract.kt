package com.checkfer.orderpay.di

import android.content.Context
import android.net.ConnectivityManager
import com.checkfer.core.network.NetworkModule
import com.checkfer.core.network.NetworkState
import com.checkfer.core.network.NetworkStateImpl
import com.checkfer.core.network.api.OrderPayApiConfig
import com.checkfer.core.network.module.OrderPayApiModule
import com.checkfer.orderpay.OrderPayApplication

interface ConfigContract {

    fun networkState(manager: ConnectivityManager): NetworkState = NetworkStateImpl(manager)

    fun orderPayApiConfig() = OrderPayApiConfig()

    fun applicationInjector(application: OrderPayApplication): ApplicationComponent {
        val connectivityManager =
            (application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        return DaggerApplicationComponent.builder()
            .context(application)
            .networkModule(NetworkModule(networkState(connectivityManager)))
            .orderPayApiModule(
                OrderPayApiModule(
                    orderPayApiConfig(), application,
                    networkState(connectivityManager)
                )
            )
            .build()
    }
}