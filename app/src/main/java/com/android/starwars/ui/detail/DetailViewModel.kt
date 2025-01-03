package com.android.starwars.ui.detail

import androidx.lifecycle.viewModelScope
import com.android.starwars.data.model.Characters
import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.model.defaultDetailModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.usecase.DetailUseCase
import com.android.starwars.domain.usecase.SearchCharacterUseCase
import com.android.starwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailUseCase: DetailUseCase) :
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState() = DetailContract.State(
        isLoading = false,
        isError = false,
        errorMessage = "",
        detail = defaultDetailModel
    )
    override fun handleEvents(event: DetailContract.Event) {

        when (event) {
            is DetailContract.Event.onBackClick -> {
            }

            is DetailContract.Event.getCharacterDetail -> {
                getDetail(id = event.id)
            }
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            detailUseCase.detail(id = id).collect{
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
                        setState { viewState.value.copy(detail = it.data as DetailResponseModel) }

                    }
                }
            }
        }
    }
}