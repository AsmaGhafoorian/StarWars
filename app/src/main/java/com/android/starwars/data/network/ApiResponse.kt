package com.android.starwars.data.network

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ErrorResponse<T> {
            return error.message?.let {
                ErrorResponse(0, it)
            } ?: ErrorResponse(0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body == null || response.code() == 204) {
                        EmptyResponse()
                    } else {
                        SuccessResponse(body)
                    }
                }

                else -> {
                    if(response.code() == 401){
                        EmptyResponse()
                    }else {
                         ErrorResponse(response.code())
                    }
                }
            }
        }
    }
}

class EmptyResponse<T> : ApiResponse<T>()
data class ErrorResponse<T>(
    val errorCode: Int,
    val message: String = "try again later."
) : ApiResponse<T>()

data class SuccessResponse<T>(val body: T) : ApiResponse<T>()