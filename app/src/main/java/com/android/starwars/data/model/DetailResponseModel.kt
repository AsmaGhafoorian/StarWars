package com.android.starwars.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailResponseModel (
    @Json(name = "name") val name : String,
    @Json(name = "height") val height : String,
    @Json(name = "birth_year") val birthYear : String,
    @Json(name = "species") val species : List<String>,
    @Json(name = "homeworld") val homeworld : String)

val defaultDetailModel = DetailResponseModel(
    name = "",
    height = "",
    birthYear = "",
    species = emptyList(),
    homeworld = ""
)