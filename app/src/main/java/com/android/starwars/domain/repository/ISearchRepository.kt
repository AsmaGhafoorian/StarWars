package com.android.starwars.domain.repository

import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.network.CustomResult
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

   suspend fun searchCharacter(query: String, reset: Boolean) : Flow<CustomResult<SearchResponseModel>>
}