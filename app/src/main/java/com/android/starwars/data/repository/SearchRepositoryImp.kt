package com.android.starwars.data.repository

import com.android.starwars.data.ApiServices
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.domain.repository.ISearchRepository
import com.android.starwars.data.network.CustomResult
import com.android.starwars.data.network.withResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val apiService: ApiServices): ISearchRepository {
    override suspend fun searchCharacter(query: String): Flow<CustomResult<SearchResponseModel>> =
         flow {
            emit(withResponse{apiService.searchCharacter(query)})
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)

}