package com.checkfer.feature.startup.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.checkfer.core.viewmodel.ViewModelFactory
import com.checkfer.feature.startup.R
import com.checkfer.feature.startup.navigation.StartupNavigator
import com.checkfer.feature.startup.viewmodel.SplashViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

internal class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var startupNavigation: StartupNavigator

    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun onAttach(context: Context) = super.onAttach(context).also {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.startup_splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        viewModel.init()
    }

    private fun subscribeObservers() {
        viewModel.splashDidLoadEvent.observe(viewLifecycleOwner, observeSplashDuration)
    }

    private val observeSplashDuration = Observer<Void> {
        Handler().postDelayed({
            // Navigate to next view
            activity!!.finish()
        }, 3000)
    }
}
