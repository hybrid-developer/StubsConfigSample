package com.checkfer.feature.startup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.checkfer.feature.startup.viewmodel.SplashViewModel
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @Mock
    lateinit var observerSingleEvent: Observer<Void>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup() {
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `when initialize the viewModel trigger splashDidLoad event`() {
        verify(observerSingleEvent, never()).onChanged(null)

        splashViewModel.splashDidLoadEvent.observeForever(observerSingleEvent)
        splashViewModel.init()
        verify(observerSingleEvent).onChanged(null)
    }
}