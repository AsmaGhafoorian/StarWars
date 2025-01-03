package com.android.starwars.ui.search

import androidx.lifecycle.viewModelScope
import com.android.starwars.data.model.Characters
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.usecase.SearchCharacterUseCase
import com.android.starwars.ui.base.BaseViewModel
import com.android.starwars.utils.getId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

                setEffect { SearchContract.Effect.Navigation.ToDetailScreen(getId(event.character.url)) }
            }

            is SearchContract.Event.search -> {
                searchCharacter(query = event.query, reset = event.reset)
            }
        }
    }

    fun searchCharacter(query: String, reset: Boolean) {
        viewModelScope.launch {
            searchCharacterUseCase.search(query = query, reset = reset).collect{
                when (it) {
                    is CustomResult.Loading -> {

                        setState{viewState.value.copy(isLoading = true)}
                    }

                    is CustomResult.Error -> {
                        setState{viewState.value.copy(isLoading = false, isError = true, errorMessage = it.exception.message)}
                    }

                    is CustomResult.Empty -> {

                    }

                    is CustomResult.Success<*> -> {
                        var result = ArrayList<Characters>()
                        viewState.value.searchResult.results?.let { it1 -> result.addAll(it1) }
                        (it.data as SearchResponseModel).results?.let { it1 -> result.addAll(it1) }
                        setState { viewState.value.copy(searchResult = SearchResponseModel(results = result)) }

                    }
                }
            }
        }
    }
}