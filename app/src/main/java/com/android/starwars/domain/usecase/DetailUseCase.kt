package com.android.starwars.domain.usecase

import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.repository.IDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val iDetailRepository: IDetailRepository) {

    suspend fun detail(id: Int): Flow<CustomResult<DetailResponseModel>> =
        iDetailRepository.characterDetail(id = id)
}