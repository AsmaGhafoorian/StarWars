package com.android.starwars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.starwars.ui.detail.DetailScreen
import com.android.starwars.ui.search.SearchContract
import com.android.starwars.ui.search.SearchScreen
import com.android.starwars.ui.splash.SplashContract
import com.android.starwars.ui.splash.SplashScreen

sealed class MainDestinations(val route: String) {
    data object Splash : MainDestinations("splash")
    data object Search : MainDestinations("search")
    data object Detail : MainDestinations("detail/{id}"){
        fun routeWithId(id: Int) =
            route.replace("{id}", id.toString())
    }
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
        composable(route = MainDestinations.Search.route) { SearchDestination(navHostController) }
        composable(route = MainDestinations.Detail.route,
            arguments = listOf(
                navArgument(name = "id") { type = NavType.IntType },
            )) {backStackEntry ->
            val id = requireNotNull(backStackEntry.arguments?.getInt("id"))

            DetailScreen(id = id) { } }
    }
}

@Composable
fun SplashDestination(
    navController: NavHostController,
) {
    SplashScreen(onNavigationRequested = { navigationEffect ->

        if (navigationEffect is SplashContract.Effect.Navigation.ToSearchScreen) {
            navController.navigate(MainDestinations.Search.route)
        }
    })
}

@Composable
fun SearchDestination(
    navController: NavHostController,
) {
    SearchScreen(onNavigationRequested = { navigationEffect ->

        if (navigationEffect is SearchContract.Effect.Navigation.ToDetailScreen) {
            navController.navigate(MainDestinations.Detail.routeWithId(navigationEffect.id))
        }
    })
}

