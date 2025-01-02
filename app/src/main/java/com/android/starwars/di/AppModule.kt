package com.android.starwars.di

import com.android.starwars.ui.interfaces.IMainNavigation
import com.android.starwars.ui.interfaces.INavControllerProvider
import com.android.starwars.ui.navigation.MainActions
import com.android.starwars.ui.navigation.NavControllerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNavigationProvider(): INavControllerProvider = NavControllerProvider()

    @Singleton
    @Provides
    fun provideNavigationAction(iNavControllerProvider: INavControllerProvider): IMainNavigation =
        MainActions(iNavControllerProvider = iNavControllerProvider)
}