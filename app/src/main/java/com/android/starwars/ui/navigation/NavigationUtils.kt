package com.android.starwars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.starwars.ui.interfaces.IMainNavigation
import com.android.starwars.ui.interfaces.INavControllerProvider
import com.android.starwars.ui.search.SearchScreen
import com.android.starwars.ui.splash.SplashScreen
import javax.inject.Inject

sealed class MainDestinations(val route: String) {
    data object Splash : MainDestinations("splash")
    data object Search : MainDestinations("search")
}

@Composable
fun MainNavigation(
    navHostController: NavHostController,
    iMainNavigation: IMainNavigation
) {
    NavHost(
        navController = navHostController,
        startDestination = MainDestinations.Splash.route
    ) {
        composable(route = MainDestinations.Splash.route) { SplashScreen(iMainNavigation = iMainNavigation) }
        composable(route = MainDestinations.Search.route) { SearchScreen() }
    }
}


class NavControllerProvider : INavControllerProvider {
    private var navController: NavHostController? = null
    override val controller: NavHostController
        get() = navController!!

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }
}

class MainActions @Inject constructor(private val iNavControllerProvider: INavControllerProvider) :
    IMainNavigation {
    private val controller
        get() = iNavControllerProvider.controller

    override fun navigateToSearchScreen() {
        controller.navigate("search")
    }
}