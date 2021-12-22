package com.olabode.wilson.cyclee.networking.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        NetworkResult<Nothing>()

    object NetworkError : NetworkResult<Nothing>()
}

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val errorMessage: String? = null
)
