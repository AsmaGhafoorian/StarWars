package com.android.starwars.ui.detail.composables

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmResponseModel(
    @Json(name = "opening_crawl") val openingCrawl: String,
    @Json(name = "title") val title: String
)

@Stable
@Immutable
data class Films(
    val films: List<FilmResponseModel>
)