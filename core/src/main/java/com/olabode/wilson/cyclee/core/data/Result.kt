package com.olabode.wilson.cyclee.core.data

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

/**
 * This is a sealed class that represents two options for a data response, where the response is
 * either successful or a failure.
 *
 * By wrapping a response into this single type, we can provide a way for asynchronous streams to
 * handle both success and failure scenarios, without having to catch exceptions. This is because any
 * exceptions will be wrapped inside an [Error] class.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(
        val error: Throwable? = null,
        val message: String? = null,
        val statusCode: Int? = null
    ) : Result<Nothing>()
}

typealias SimpleResult = Result<Unit>
