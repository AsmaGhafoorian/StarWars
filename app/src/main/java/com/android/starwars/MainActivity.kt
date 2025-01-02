package com.android.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.android.starwars.ui.interfaces.IMainNavigation
import com.android.starwars.ui.interfaces.INavControllerProvider
import com.android.starwars.ui.navigation.MainNavigation
import com.android.starwars.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var iNavControllerProvider: INavControllerProvider

    @Inject
    lateinit var iMainNavigation: IMainNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StarWarsTheme {

                MainContent(
                    iNavControllerProvider = iNavControllerProvider,
                    iMainNavigation = iMainNavigation
                )
            }
        }
    }
}

@Composable
fun MainContent(
    iNavControllerProvider: INavControllerProvider,
    iMainNavigation: IMainNavigation
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        iNavControllerProvider.setNavController(navController)
    }
    MainNavigation(
        iMainNavigation = iMainNavigation,
        navHostController = navController
    )
}
