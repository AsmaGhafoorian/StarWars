package com.android.starwars.domain.repository

import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.model.SpeciesResponseModel
import com.android.starwars.data.network.CustomResult
import kotlinx.coroutines.flow.Flow

interface ISpeciesRepository {

   suspend fun species(id: Int) : Flow<CustomResult<SpeciesResponseModel>>
}