package com.android.starwars.ui.search

import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.ui.base.ViewEffect
import com.android.starwars.ui.base.ViewEvent
import com.android.starwars.ui.base.ViewState
import kotlinx.coroutines.flow.Flow


class SearchContract {

    sealed class Event : ViewEvent {
        object onCharacterClick : Event()
    }

    data class State(
        val searchResult: SearchResponseModel,
        val isLoading: Boolean,
        val isError: Boolean,
        val errorMessage: String
    ) : ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object ToDetailScreen : Navigation()
        }
    }
}