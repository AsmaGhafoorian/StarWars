package com.android.starwars.ui.detail

import androidx.lifecycle.viewModelScope
import com.android.starwars.data.model.Characters
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.usecase.SearchCharacterUseCase
import com.android.starwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() :
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState() = DetailContract.State(
        isLoading = false,
        isError = false,
        errorMessage = "",
    )
    override fun handleEvents(event: DetailContract.Event) {

        when (event) {
            is DetailContract.Event.onBackClick -> {
            }

            is DetailContract.Event.getCharacterDetail -> {

            }
        }
    }

    fun searchCharacter(query: String, reset: Boolean) {
        viewModelScope.launch {
//            searchCharacterUseCase.search(query = query, reset = reset).collect{
//                when (it) {
//                    is CustomResult.Loading -> {
//
//                        setState{viewState.value.copy(isLoading = true)}
//                    }
//
//                    is CustomResult.Error -> {
//                        setState{viewState.value.copy(isLoading = false, isError = true, errorMessage = it.exception.message)}
//                    }
//
//                    is CustomResult.Empty -> {
//
//                    }
//
//                    is CustomResult.Success<*> -> {
//                        var result = ArrayList<Characters>()
//                        viewState.value.searchResult.results?.let { it1 -> result.addAll(it1) }
//                        (it.data as SearchResponseModel).results?.let { it1 -> result.addAll(it1) }
//                        setState { viewState.value.copy(searchResult = SearchResponseModel(results = result)) }
//
//                    }
//                }
//            }
        }
    }
}