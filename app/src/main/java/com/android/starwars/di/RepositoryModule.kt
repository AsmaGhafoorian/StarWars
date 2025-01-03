package com.android.starwars.di

import com.android.starwars.data.ApiServices
import com.android.starwars.data.repository.SearchRepositoryImp
import com.android.starwars.domain.repository.ISearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideConfigRepository(api: ApiServices): ISearchRepository {
        return SearchRepositoryImp(api)
    }

}