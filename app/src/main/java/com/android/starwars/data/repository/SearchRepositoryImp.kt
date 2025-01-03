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
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val apiService: ApiServices): ISearchRepository {


    companion object {
        private const val PAGE_SIZE = 10
        private var pageNumber = 1
    }
    override suspend fun searchCharacter(query: String, reset: Boolean): Flow<CustomResult<SearchResponseModel>> {
        if(reset)
            pageNumber = 1

        return flow {
            emit(withResponse { apiService.searchCharacter(query = query, page = pageNumber) })
        }.onStart {
            emit(CustomResult.Loading)
        }.onCompletion {
            if (it == null) pageNumber += 1
        }.flowOn(Dispatchers.IO)

    }
}