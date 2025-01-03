package com.android.starwars.data

import com.android.starwars.data.model.SearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("people")
    suspend fun searchCharacter(
        @Query("search") query: String,
        @Query("page") page: Int,
    ): Response<SearchResponseModel>
}