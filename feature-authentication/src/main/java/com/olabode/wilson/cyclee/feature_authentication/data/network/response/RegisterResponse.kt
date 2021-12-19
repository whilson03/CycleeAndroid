package com.olabode.wilson.cyclee.feature_authentication.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "_id")
    val id: String,
    @Json(name = "email")
    val email: String
)
