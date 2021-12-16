package com.olabode.wilson.cyclee.networking.domain.models

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        NetworkResult<Nothing>()

    object NetworkError : NetworkResult<Nothing>()
}

class ErrorResponse
