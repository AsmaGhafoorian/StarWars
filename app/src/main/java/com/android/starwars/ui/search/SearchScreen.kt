package com.android.starwars.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.starwars.R
import com.android.starwars.data.model.Characters
import com.android.starwars.ui.search.composables.SearchBox
import com.android.starwars.ui.splash.SplashContract
import com.android.starwars.utils.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: SearchContract.Effect.Navigation) -> Unit,
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    var query by remember { mutableStateOf("") }
    val data = state.value.searchResult
    val isLoading = state.value.isLoading

    val effetct = rememberFlowWithLifecycle(viewModel.effect)

    LaunchedEffect(effetct) {
        effetct.collect {
            when (it) {
                is SearchContract.Effect.Navigation.ToDetailScreen -> {
                    onNavigationRequested(SearchContract.Effect.Navigation.ToDetailScreen(it.id))
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 30.dp, start = 24.dp, end = 24.dp)
    ) {
        SearchBox(
            input = query,
            placeholder = stringResource(R.string.search),
            onValueChange = {
                query = it
                viewModel.setEvent(SearchContract.Event.search(it, true))
            })

        data.results?.let {
            SearchContent(data = it, isLoading = isLoading,
                loadMoreItems = { viewModel.setEvent(SearchContract.Event.search(query, false)) },
                onSelectCharacter = {
                    viewModel.setEvent(SearchContract.Event.onCharacterClick(it))
                })
        }

    }

}

@Composable
fun SearchContent(
    data: List<Characters>,
    isLoading: Boolean,
    loadMoreItems: () -> Unit,
    onSelectCharacter: (Characters) -> Unit
) {
    val listState: LazyListState = rememberLazyListState()
    val buffer = 3
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            if (totalItemsCount < 10)
                false
            else {
                val lastVisibleItemIndex =
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleItemIndex >= (totalItemsCount - buffer) && !isLoading
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }  // Ensure that we load more items only when needed
            .collect {
                loadMoreItems()
            }
    }
    LazyColumn(
        state = listState
    ) {
        items(data) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
                    .clickable { onSelectCharacter(it) },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.default_img),
                        contentDescription = "default",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = it.name,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold

                    )

                }
                Icon(
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = "arrow",
                    tint = Color.Unspecified,
                )
            }
        }
    }

}