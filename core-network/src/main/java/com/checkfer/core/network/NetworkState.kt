package com.checkfer.core.network

import android.net.ConnectivityManager
import javax.inject.Inject

interface NetworkState {
    fun isNetworkConnected(): Boolean
}

class NetworkStateImpl @Inject constructor(private val connectivityManager: ConnectivityManager) :
    NetworkState {

    override fun isNetworkConnected(): Boolean =
        connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
}