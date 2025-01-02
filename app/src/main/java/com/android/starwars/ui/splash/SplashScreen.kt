package com.android.starwars.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.starwars.R
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment


@Composable
fun SplashScreen() {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(R.drawable.splach_background),
            contentDescription = "splash",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth,
        )
        Image(
            painter = painterResource(R.drawable.starwars),
            contentDescription = "splash",
            modifier = Modifier
                .padding(bottom = 60.dp)


        )

    }
}