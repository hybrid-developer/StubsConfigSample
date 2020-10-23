package com.checkfer.orderpay

import androidx.lifecycle.LifecycleCallbacks
import com.checkfer.orderpay.di.ApplicationComponent
import dagger.android.DaggerApplication

class OrderPayApplication : DaggerApplication() {

    override fun onCreate() = super.onCreate().also {
        registerActivityLifecycleCallbacks(LifecycleCallbacks.activityListener())
    }

    override fun applicationInjector(): ApplicationComponent = Config.applicationInjector(this)
}