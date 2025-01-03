package com.android.starwars.domain.usecase

import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.repository.IPlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetUseCase @Inject constructor(private val iPlanetRepository: IPlanetRepository) {

    suspend fun invoke(id: Int): Flow<CustomResult<PlanetResponseModel>> =
        iPlanetRepository.planet(id = id)
}