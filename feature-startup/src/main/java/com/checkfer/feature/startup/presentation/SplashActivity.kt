package com.checkfer.feature.startup.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.checkfer.feature.startup.R
import dagger.android.AndroidInjection

internal class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup_splash_activity)
        AndroidInjection.inject(this)
    }
}