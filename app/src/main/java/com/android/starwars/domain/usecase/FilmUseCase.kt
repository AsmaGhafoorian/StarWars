package com.android.starwars.domain.usecase

import com.android.starwars.ui.detail.composables.FilmResponseModel
import com.android.starwars.data.network.CustomResult
import com.android.starwars.domain.repository.IFilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmUseCase @Inject constructor(private val iFilmRepository: IFilmRepository) {

    suspend fun invoke(ids: List<Int>): Flow<CustomResult<FilmResponseModel>> =
        iFilmRepository.films(ids = ids)
}