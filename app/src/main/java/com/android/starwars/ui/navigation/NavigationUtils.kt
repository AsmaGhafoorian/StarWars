package com.android.starwars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.starwars.ui.search.SearchScreen
import com.android.starwars.ui.splash.SplashContract
import com.android.starwars.ui.splash.SplashScreen

sealed class MainDestinations(val route: String) {
    data object Splash : MainDestinations("splash")
    data object Search : MainDestinations("search")
}

@Composable
fun MainNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = MainDestinations.Splash.route
    ) {
        composable(route = MainDestinations.Splash.route) { SplashDestination(navHostController) }
        composable(route = MainDestinations.Search.route) { SearchScreen() }
    }
}

@Composable
fun SplashDestination(
    navController: NavHostController,
) {
    SplashScreen(onNavigationRequested = { navigationEffect ->

        if (navigationEffect is SplashContract.Effect.Navigation.ToSearchScreen) {
            navController.navigate("search")
        }
    })
}

