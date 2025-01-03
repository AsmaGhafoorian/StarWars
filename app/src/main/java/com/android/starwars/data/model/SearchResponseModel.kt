package com.android.starwars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponseModel (
    @Json(name = "results") val results : List<Characters> ?
)
@JsonClass(generateAdapter = true)
data class Characters (
    @Json(name = "name") val name : String,
    @Json(name = "url") val url : String
)
