package com.android.starwars.ui.search

import com.android.starwars.data.model.Characters
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.ui.base.ViewEffect
import com.android.starwars.ui.base.ViewEvent
import com.android.starwars.ui.base.ViewState
import com.android.starwars.ui.detail.DetailContract.Event


class SearchContract {

    sealed class Event : ViewEvent {
        data class onCharacterClick(val character: Characters) : Event()
        data class search(val query: String, val reset: Boolean) : Event()
        object onBackClick : Event()

    }

    data class State(
        val searchResult: SearchResponseModel,
        val isLoading: Boolean,
        val isError: Boolean,
        val errorMessage: String?
    ) : ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            data class ToDetailScreen(val id: Int) : Navigation()
            object GoBack : Navigation()
        }
    }
}