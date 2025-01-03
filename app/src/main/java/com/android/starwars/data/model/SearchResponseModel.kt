package com.android.starwars.data.model

data class SearchResponseModel (
    val results : List<Character> ?
)

data class Character (
    val name : String,
    val url : String
)
