package com.android.starwars.ui.detail

import androidx.lifecycle.viewModelScope
import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.model.SpeciesResponseModel
import com.android.starwars.data.model.defaultDetailModel
import com.android.starwars.data.model.defaultPlanet
import com.android.starwars.data.model.defaultSpecies
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.usecase.DetailUseCase
import com.android.starwars.domain.usecase.PlanetUseCase
import com.android.starwars.domain.usecase.SpeciesUseCase
import com.android.starwars.ui.base.BaseViewModel
import com.android.starwars.utils.getId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase,
    private val planetUseCase: PlanetUseCase,
    private val speciesUseCase: SpeciesUseCase
) :
    BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {
    override fun setInitialState() = DetailContract.State(
        isLoading = false,
        isError = false,
        errorMessage = "",
        detail = defaultDetailModel,
        species = defaultSpecies,
        planet = defaultPlanet
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
            detailUseCase.detail(id = id).collect {
                when (it) {
                    is CustomResult.Loading -> {
                        setState { viewState.value.copy(isLoading = true) }
                    }
                    is CustomResult.Error -> {
                        setState {
                            viewState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = it.exception.message
                            )
                        }
                    }

                    is CustomResult.Empty -> {
                    }

                    is CustomResult.Success<*> -> {
                        setState { viewState.value.copy(detail = it.data as DetailResponseModel) }
                        val planetId = getId((it.data as DetailResponseModel).homeworld)
                        val speciesId = getId(it.data .species[0])
                        getAdditionalData(planetId = planetId, speciesId = speciesId)
                    }
                }
            }
        }
    }
    fun getAdditionalData(planetId: Int, speciesId: Int){
        viewModelScope.launch {
           val planet = async { planetUseCase.invoke(id = planetId) }
            val species = async { speciesUseCase.invoke(id = speciesId) }
            planet.await().collect {
                when (it) {
                    is CustomResult.Loading -> {
                        setState { viewState.value.copy(isLoading = true) }
                    }
                    is CustomResult.Error -> {
                        setState {
                            viewState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = it.exception.message
                            )
                        }
                    }

                    is CustomResult.Empty -> {
                    }

                    is CustomResult.Success<*> -> {
                        setState { viewState.value.copy(planet = it.data as PlanetResponseModel) }
                    }
                }
            }
            species.await().collect {
                when (it) {
                    is CustomResult.Loading -> {
                        setState { viewState.value.copy(isLoading = true) }
                    }
                    is CustomResult.Error -> {
                        setState {
                            viewState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMessage = it.exception.message
                            )
                        }
                    }

                    is CustomResult.Empty -> {
                    }

                    is CustomResult.Success<*> -> {
                        setState { viewState.value.copy(species = it.data as SpeciesResponseModel) }
                    }
                }
            }
        }
    }
}