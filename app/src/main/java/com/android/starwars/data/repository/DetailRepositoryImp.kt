package com.android.starwars.data.repository

import com.android.starwars.data.ApiServices
import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.data.network.withResponse
import com.android.starwars.domain.repository.IDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(private val apiService: ApiServices): IDetailRepository {

    override suspend fun characterDetail(id: Int): Flow<CustomResult<DetailResponseModel>> =
        flow {
            emit(withResponse { apiService.characterDetail(id = id) })
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)
}