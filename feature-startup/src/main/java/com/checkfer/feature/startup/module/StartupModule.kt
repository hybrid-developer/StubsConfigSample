package com.checkfer.feature.startup.module

import com.checkfer.feature.startup.presentation.SplashActivity
import com.checkfer.feature.startup.presentation.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartupModule {

    @ContributesAndroidInjector(modules = [StartupFragmentModule::class])
    internal abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity
}