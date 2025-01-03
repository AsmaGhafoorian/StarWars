package com.android.starwars.di

import com.android.starwars.data.ApiServices
import com.android.starwars.data.repository.DetailRepositoryImp
import com.android.starwars.data.repository.SearchRepositoryImp
import com.android.starwars.domain.repository.IDetailRepository
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
    fun provideSearchRepository(api: ApiServices): ISearchRepository {
        return SearchRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(api: ApiServices): IDetailRepository {
        return DetailRepositoryImp(api)
    }

}