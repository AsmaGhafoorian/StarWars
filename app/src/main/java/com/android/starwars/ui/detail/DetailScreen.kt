package com.android.starwars.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.starwars.R
import com.android.starwars.ui.detail.composables.FilmResponseModel
import com.android.starwars.ui.detail.composables.Films


@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: DetailContract.Effect.Navigation) -> Unit,
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    val data = state.value.detail
    val species = state.value.species
    val planet = state.value.planet
    val films = state.value.films
    var selectedFilm by remember { mutableStateOf<FilmResponseModel?>(null) }

    LaunchedEffect(Unit) {
        viewModel.setEvent(DetailContract.Event.getCharacterDetail(id = id))

    }
    LaunchedEffect(films) {
        if (films.films.isNotEmpty() && selectedFilm == null) {
            selectedFilm = films.films[0]
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "arrow",
                tint = Color.Unspecified,
            )
            Icon(
                painter = painterResource(R.drawable.ic_favorite),
                contentDescription = "arrow",
                tint = Color.Unspecified,
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.default_character_img),
                        contentDescription = "img",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = data.name,
                        fontSize = 26.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = species.name,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W500
                    )

                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.birth),
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold

                            )
                            Text(
                                text = data.birthYear,
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal

                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = stringResource(R.string.height),
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold

                            )
                            Text(
                                text = "${data.height} cm",
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal

                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = stringResource(R.string.language),
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold

                            )
                            Text(
                                text = species.language,
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal

                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.population),
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold

                            )
                            Text(
                                text = planet.population,
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal

                            )
                        }
                    }
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )
            }
            item { FilmsContent(data = films, onSelectFilm = { selectedFilm = it }) }
            item {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )
            }
            item {
                Text(
                    text = selectedFilm?.openingCrawl ?: "",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                )
            }

        }
    }
}

@Composable
fun FilmsContent(data: Films, onSelectFilm: (FilmResponseModel) -> Unit) {
    LazyRow {
        items(data.films) {
            Column(modifier = Modifier
                .width(130.dp)
                .padding(10.dp)
                .clickable { onSelectFilm(it) }) {
                Image(
                    painter = painterResource(R.drawable.film_background),
                    contentDescription = "film",
                    modifier = Modifier.size(100.dp, 150.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it.title,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal

                )
            }
        }
    }
}