package com.olabode.wilson.cyclee.networking.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

/**
 * data class for cases where the API only returns a message.
 */
@JsonClass(generateAdapter = true)
data class BasicApiResponse(
    @Json(name = "message")
    val message: String? = null
)
