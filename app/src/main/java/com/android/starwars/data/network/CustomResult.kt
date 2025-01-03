package com.android.starwars.data.network

sealed class CustomResult<out T> {

    data class Success<out T>(val data: T) : CustomResult<T>() {
        operator fun invoke(): T {
            return data
        }
    }
    data class Error(val exception: Throwable) : CustomResult<Nothing>()
    object Loading : CustomResult<Nothing>()
    object Empty : CustomResult<Nothing>()
}