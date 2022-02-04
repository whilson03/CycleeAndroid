package com.olabode.wilson.cyclee.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.olabode.wilson.cyclee.R
import com.olabode.wilson.cyclee.ui.navigation.MainScreen

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

enum class BottomNavigationItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, MainScreen.Feeds.route),
    EXPLORE(R.string.home_explore, Icons.Outlined.Search, MainScreen.Explore.route),
    ACTIVITIES(R.string.home_activities, Icons.Outlined.LocationOn, MainScreen.Activities.route),
    PROFILE(R.string.home_profile, Icons.Outlined.AccountCircle, MainScreen.Profile.route),
}
