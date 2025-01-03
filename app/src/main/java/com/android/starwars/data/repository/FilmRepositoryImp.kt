package com.android.starwars.data.repository

import com.android.starwars.data.ApiServices
import com.android.starwars.ui.detail.composables.FilmResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.data.network.withResponse
import com.android.starwars.domain.repository.IFilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(private val apiService: ApiServices) :
    IFilmRepository {

    override suspend fun films(ids: List<Int>): Flow<CustomResult<FilmResponseModel>> {
        val flows = mutableListOf<Flow<CustomResult<FilmResponseModel>>>()
        ids.map { id ->
            flows.add(
                flow {
                    emit(withResponse { apiService.film(id = id) })
                }
            )
        }
        val retFlow = if (flows.size > 0)
            flows.merge()
        else
            flow { emit(CustomResult.Empty) }

        return retFlow.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)
    }

}