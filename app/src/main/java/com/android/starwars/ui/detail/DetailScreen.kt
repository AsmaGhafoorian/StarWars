package com.android.starwars.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: DetailContract.Effect.Navigation) -> Unit,
) {
    Text(id.toString())
}