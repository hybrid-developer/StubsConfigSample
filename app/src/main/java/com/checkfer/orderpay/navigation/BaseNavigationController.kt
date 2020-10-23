package com.checkfer.orderpay.navigation

import android.view.View
import javax.inject.Inject

internal open class BaseNavigationController @Inject constructor() {

    fun showHomeScreen(view: View) {
        // findNavController(view).navigate(R.id.homeFragment)
    }
}