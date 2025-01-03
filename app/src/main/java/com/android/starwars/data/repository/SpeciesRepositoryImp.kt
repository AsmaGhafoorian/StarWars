package com.android.starwars.data.repository

import com.android.starwars.data.ApiServices
import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.model.SpeciesResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.data.network.withResponse
import com.android.starwars.domain.repository.IPlanetRepository
import com.android.starwars.domain.repository.ISpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SpeciesRepositoryImp @Inject constructor(private val apiService: ApiServices) :
    ISpeciesRepository {

    override suspend fun species(id: Int): Flow<CustomResult<SpeciesResponseModel>> =
        flow {
            emit(withResponse { apiService.species(id = id) })
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)


}