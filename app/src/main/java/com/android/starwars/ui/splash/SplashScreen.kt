package com.android.starwars.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.starwars.R
import com.android.starwars.ui.theme.StarWarsTheme
import com.android.starwars.utils.rememberFlowWithLifecycle


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: SplashContract.Effect.Navigation) -> Unit,
) {

    val effetct = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(effetct) {
        effetct.collect {
            when (it) {
                is SplashContract.Effect.Navigation.ToSearchScreen -> {
                    onNavigationRequested(SplashContract.Effect.Navigation.ToSearchScreen)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.splach_background),
            contentDescription = "splash",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .padding(bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.starwars),
                contentDescription = "splash",
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { viewModel.setEvent(SplashContract.Event.onStartClick) },
            ) {
                Text(text = stringResource(R.string.start))
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    StarWarsTheme {
//        SplashScreen(iMainNavigation = IMainNavigation())
    }
}