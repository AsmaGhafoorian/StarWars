package com.android.starwars.domain.usecase

import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor( private val iSearchRepository: ISearchRepository) {

    suspend fun search(query: String, reset: Boolean) : Flow<CustomResult<SearchResponseModel>>
    = iSearchRepository.searchCharacter(query, reset)
}