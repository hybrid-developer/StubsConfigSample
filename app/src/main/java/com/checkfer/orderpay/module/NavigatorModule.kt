package com.checkfer.orderpay.module

import com.checkfer.feature.startup.navigation.StartupNavigator
import com.checkfer.orderpay.navigation.StartupNavigationController
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {

    @Binds
    internal abstract fun bindStartupNavigator(impl: StartupNavigationController): StartupNavigator
}