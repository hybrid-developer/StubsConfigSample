package com.checkfer.orderpay.navigation

import android.view.View
import com.checkfer.feature.startup.navigation.StartupNavigator
import javax.inject.Inject

internal class StartupNavigationController @Inject constructor() :
    BaseNavigationController(), StartupNavigator {

    override fun navigateToHomeScreen(view: View) = showHomeScreen(view)
}