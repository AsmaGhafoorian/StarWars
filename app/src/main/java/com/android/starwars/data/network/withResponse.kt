package com.android.starwars.data.network

import retrofit2.Response

inline fun <T> withResponse(function: () -> Response<T>): CustomResult<T> {
    return try {
        when (val response = ApiResponse.create(function.invoke())) {
            is SuccessResponse -> {
                println("aaaaaaa: " + response.body)
                CustomResult.Success(response.body)
            }
            is ErrorResponse -> when (response.errorCode) {
                400 -> CustomResult.Error(Response400Exception(response.message))
                401 -> CustomResult.Error(Response401Exception(response.message))
                403 -> CustomResult.Error(Response403Exception(response.message))
                500 -> CustomResult.Error(Response500Exception(response.message))
                else -> CustomResult.Error(RemoteUnknownException(response.message))
            }
            //empty response
            else -> CustomResult.Empty
        }
    } catch (e: Exception) {
        e.message?.let {
            CustomResult.Error(RemoteUnknownException(it))
        } ?: throw e
    }
}

class Response400Exception(message: String) : Exception(message)
class Response401Exception(message: String) : Exception(message)
class Response403Exception(message: String) : Exception(message)
class Response500Exception(message: String) : Exception(message)
class RemoteUnknownException(message: String) : Exception(message)