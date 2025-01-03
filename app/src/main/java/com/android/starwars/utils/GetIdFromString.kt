package com.android.starwars.utils

fun getId(input: String) : Int{
    val url = input
    val lastSlashIndex = url.lastIndexOf("/")
    val secondLastSlashIndex = url.lastIndexOf("/", lastSlashIndex - 1)
    return url.substring(secondLastSlashIndex + 1, lastSlashIndex).toInt()

}