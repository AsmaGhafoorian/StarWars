package com.android.starwars.ui.splash

import com.android.starwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {
    override fun setInitialState() = SplashContract.State
    override fun handleEvents(event: SplashContract.Event) {

        when (event) {
            is SplashContract.Event.onStartClick -> {
                setEffect { SplashContract.Effect.Navigation.ToSearchScreen }
            }
        }
    }
}