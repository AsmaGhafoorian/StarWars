package com.android.starwars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SpeciesResponseModel (
    @Json(name = "name") val name : String,
    @Json(name = "language") val language : String,
    @Json(name = "homeworld") val homeworld : String,
)

val defaultSpecies = SpeciesResponseModel(name = "", language = "", homeworld = "")