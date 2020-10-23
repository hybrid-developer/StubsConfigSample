package com.checkfer.orderpay.module

import com.checkfer.orderpay.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity
}