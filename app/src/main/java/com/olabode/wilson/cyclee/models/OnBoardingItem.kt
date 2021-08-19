package com.olabode.wilson.cyclee.models

import androidx.annotation.DrawableRes

data class OnBoardingItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val subtitle: String
)
