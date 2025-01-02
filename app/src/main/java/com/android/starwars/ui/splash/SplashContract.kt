package com.android.starwars.ui.splash

import com.android.starwars.ui.base.ViewEffect
import com.android.starwars.ui.base.ViewEvent
import com.android.starwars.ui.base.ViewState


class SplashContract {

    sealed class Event : ViewEvent {
        object onStartClick : Event()
    }

    object State : ViewState {}

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object ToSearchScreen : Navigation()
        }
    }
}