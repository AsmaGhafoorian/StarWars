package com.android.starwars.domain.repository

import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.network.CustomResult
import kotlinx.coroutines.flow.Flow

interface IDetailRepository {

    suspend fun characterDetail(id: Int) : Flow<CustomResult<DetailResponseModel>>

}