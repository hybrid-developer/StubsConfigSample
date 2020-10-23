package com.checkfer.feature.startup.module

import androidx.lifecycle.ViewModel
import com.checkfer.core.viewmodel.ViewModelKey
import com.checkfer.feature.startup.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartupFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}