package com.android.starwars.ui.detail

import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.model.SpeciesResponseModel
import com.android.starwars.ui.base.ViewEffect
import com.android.starwars.ui.base.ViewEvent
import com.android.starwars.ui.base.ViewState
import com.android.starwars.ui.detail.composables.Films


class DetailContract {

    sealed class Event : ViewEvent {
        object onBackClick : Event()
        data class getCharacterDetail(val id: Int) : Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val errorMessage: String?,
        val detail: DetailResponseModel,
        val planet: PlanetResponseModel,
        val species: SpeciesResponseModel,
        val films: Films
    ) : ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object GoBack : Navigation()
        }
    }
}