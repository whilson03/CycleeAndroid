package com.olabode.wilson.cyclee.feature_onboarding.data

import com.olabode.wilson.cyclee.feature_onboarding.R

object OnBoardingData {

    const val dummySubtitle = "Lorem ipsum dolor sit amet, consecr " +
        "adipiscing elit. Nunc et efficitur urna. Phasellus" +
        " leo lacus, scelerisque sed tellus non, dignissim fringilla est."

    const val dummytitle = "Track your ride."

    fun getOnBoardingContentList(): List<com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem> {
        return listOf(
            com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem(
                imageRes = R.drawable.track_ride,
                title = "Track your ride.",
                subtitle = dummySubtitle
            ),

            com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem(
                imageRes = R.drawable.know_terrain,
                title = "Know your terrain.",
                subtitle = dummySubtitle
            ),

            com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem(
                imageRes = R.drawable.meet_other_cyclist,
                title = "Meet other cyclist.",
                subtitle = dummySubtitle
            ),

            com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem(
                imageRes = R.drawable.group_ride,
                title = "Join a group ride.",
                subtitle = dummySubtitle
            )
        )
    }
}
