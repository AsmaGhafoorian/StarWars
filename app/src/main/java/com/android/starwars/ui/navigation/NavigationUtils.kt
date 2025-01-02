package com.android.starwars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.starwars.ui.splash.SplashScreen

sealed class MainDestinations(val route: String) {
    data object Splash : MainDestinations("splash")
}

@Composable
fun MainNavigation(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainDestinations.Splash.route
    ) {
        composable(route = MainDestinations.Splash.route) { SplashScreen() }

    }
}