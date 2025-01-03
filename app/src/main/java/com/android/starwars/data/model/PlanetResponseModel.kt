package com.android.starwars.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PlanetResponseModel (
    @Json(name = "population") val population : String)

val defaultPlanet = PlanetResponseModel(population = "")
