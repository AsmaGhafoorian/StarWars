package com.android.starwars.ui.interfaces

import androidx.navigation.NavHostController

interface INavControllerProvider {

    val controller: NavHostController

    fun setNavController(navController: NavHostController)
}