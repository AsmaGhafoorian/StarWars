package com.android.starwars.ui.search

import androidx.lifecycle.viewModelScope
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.domain.usecase.SearchCharacterUseCase
import com.android.starwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor( private val searchCharacterUseCase: SearchCharacterUseCase) :
    BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {
    override fun setInitialState() = SearchContract.State(
        isLoading = false,
        isError = false,
        errorMessage = "",
        searchResult = SearchResponseModel(results = emptyList())
    )
    override fun handleEvents(event: SearchContract.Event) {

        when (event) {
            is SearchContract.Event.onCharacterClick -> {
                setEffect { SearchContract.Effect.Navigation.ToDetailScreen }
            }
        }
    }

    init {
        viewModelScope.launch {
            searchCharacterUseCase.search("re")
        }
    }
}