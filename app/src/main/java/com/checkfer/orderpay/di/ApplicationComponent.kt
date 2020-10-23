package com.checkfer.orderpay.di

import android.content.Context
import com.checkfer.core.network.NetworkModule
import com.checkfer.core.network.module.OrderPayApiModule
import com.checkfer.orderpay.OrderPayApplication
import com.checkfer.feature.startup.module.StartupModule
import com.checkfer.orderpay.module.MainActivityModule
import com.checkfer.orderpay.module.NavigatorModule
import com.checkfer.orderpay.util.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NavigatorModule::class,
        NetworkModule::class,
        MainActivityModule::class,
        StartupModule::class,
        OrderPayApiModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<OrderPayApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun orderPayApiModule(orderPayApiModule: OrderPayApiModule): Builder

        fun build(): ApplicationComponent
    }
}