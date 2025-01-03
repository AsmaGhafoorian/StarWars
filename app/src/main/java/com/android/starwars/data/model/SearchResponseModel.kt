package com.android.starwars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponseModel (
    @Json(name = "results") val results : List<Character> ?
)
@JsonClass(generateAdapter = true)
data class Character (
    @Json(name = "name") val name : String,
    @Json(name = "url") val url : String
)
