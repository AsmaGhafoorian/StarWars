package com.android.starwars.data

import com.android.starwars.data.model.DetailResponseModel
import com.android.starwars.data.model.PlanetResponseModel
import com.android.starwars.data.model.SearchResponseModel
import com.android.starwars.data.model.SpeciesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("people")
    suspend fun searchCharacter(
        @Query("search") query: String,
        @Query("page") page: Int,
    ): Response<SearchResponseModel>

    @GET("people/{id}")
    suspend fun characterDetail(
        @Path("id") id: Int,
    ): Response<DetailResponseModel>

    @GET("planets/{id}")
    suspend fun planet(
        @Path("id") id: Int,
    ): Response<PlanetResponseModel>

    @GET("species/{id}")
    suspend fun species(
        @Path("id") id: Int,
    ): Response<SpeciesResponseModel>
}