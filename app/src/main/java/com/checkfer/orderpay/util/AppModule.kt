package com.checkfer.orderpay.util

import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    internal abstract fun bindAppConfigurations(impl: AppConfigurationsImpl): AppConfigurations
}
