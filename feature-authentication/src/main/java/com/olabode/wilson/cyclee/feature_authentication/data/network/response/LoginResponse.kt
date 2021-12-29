package com.olabode.wilson.cyclee.feature_authentication.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */
@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "access_token")
    val accessToken: String
)
