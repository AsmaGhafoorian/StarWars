package com.android.starwars.domain.usecase

import com.android.starwars.data.model.SpeciesResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.repository.ISpeciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpeciesUseCase @Inject constructor(private val ISpeciesRepository: ISpeciesRepository) {

    suspend fun invoke(id: Int): Flow<CustomResult<SpeciesResponseModel>> =
        ISpeciesRepository.species(id = id)
}