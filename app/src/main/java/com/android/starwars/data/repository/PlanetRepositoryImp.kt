package com.android.starwars.data.repository

import com.android.starwars.data.ApiServices
import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.data.network.withResponse
import com.android.starwars.domain.repository.IPlanetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PlanetRepositoryImp @Inject constructor(private val apiService: ApiServices) :
    IPlanetRepository {

    override suspend fun planet(id: Int): Flow<CustomResult<PlanetResponseModel>> =
        flow {
            emit(withResponse { apiService.planet(id = id) })
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)

}