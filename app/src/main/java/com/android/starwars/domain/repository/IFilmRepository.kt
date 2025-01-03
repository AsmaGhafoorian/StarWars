package com.android.starwars.domain.repository

import com.android.starwars.ui.detail.composables.FilmResponseModel
import com.android.starwars.data.network.CustomResult
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {

   suspend fun films(ids: List<Int>) : Flow<CustomResult<FilmResponseModel>>
}