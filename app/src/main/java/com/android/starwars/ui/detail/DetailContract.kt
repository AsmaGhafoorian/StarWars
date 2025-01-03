package com.android.starwars.ui.detail

import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.ui.base.ViewEffect
import com.android.starwars.ui.base.ViewEvent
import com.android.starwars.ui.base.ViewState


class DetailContract {

    sealed class Event : ViewEvent {
        object onBackClick : Event()
        data class getCharacterDetail(val id: Int) : Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val errorMessage: String?,
        val detail: DetailResponseModel
    ) : ViewState
    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object ToPreviousScreen : Navigation()
        }
    }
}