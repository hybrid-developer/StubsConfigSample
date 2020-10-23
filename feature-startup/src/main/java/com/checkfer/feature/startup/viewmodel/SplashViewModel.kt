package com.checkfer.feature.startup.viewmodel

import androidx.lifecycle.ViewModel
import com.checkfer.core.viewmodel.SingleLiveEvent
import javax.inject.Inject

internal class SplashViewModel @Inject constructor() : ViewModel() {

    val splashDidLoadEvent = SingleLiveEvent<Void>()

    fun init() {
        splashDidLoadEvent.call()
    }
}
