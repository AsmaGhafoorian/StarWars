package com.android.starwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.android.starwars.ui.navigation.MainNavigation
import com.android.starwars.ui.theme.StarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StarWarsTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent(
) {
    val navController = rememberNavController()

    MainNavigation(
        navHostController = navController
    )
}
