package com.checkfer.orderpay.util

import com.checkfer.orderpay.BuildConfig
import javax.inject.Inject

interface AppConfigurations {
    fun appVersion(): String
}

class AppConfigurationsImpl @Inject constructor() : AppConfigurations {
    override fun appVersion() = BuildConfig.VERSION_NAME
}
