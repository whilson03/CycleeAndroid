package com.olabode.wilson.cyclee.feature_onboarding.domain

import androidx.annotation.DrawableRes

data class OnBoardingItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val subtitle: String
)
