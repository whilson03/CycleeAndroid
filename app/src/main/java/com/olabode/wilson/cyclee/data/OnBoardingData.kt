package com.olabode.wilson.cyclee.data

import com.olabode.wilson.cyclee.R
import com.olabode.wilson.cyclee.models.OnBoardingItem

object OnBoardingData {

    const val dummySubtitle = "Lorem ipsum dolor sit amet, consecr " +
        "adipiscing elit. Nunc et efficitur urna. Phasellus" +
        " leo lacus, scelerisque sed tellus non, dignissim fringilla est."

    const val dummytitle = "Track your ride."

    fun getOnBoardingContentList(): List<OnBoardingItem> {
        return listOf(
            OnBoardingItem(
                imageRes = R.drawable.track_ride,
                title = "Track your ride.",
                subtitle = dummySubtitle
            ),

            OnBoardingItem(
                imageRes = R.drawable.know_terrain,
                title = "Know your terrain.",
                subtitle = dummySubtitle
            ),

            OnBoardingItem(
                imageRes = R.drawable.meet_other_cyclist,
                title = "Meet other cyclist.",
                subtitle = dummySubtitle
            ),

            OnBoardingItem(
                imageRes = R.drawable.group_ride,
                title = "Join a group ride.",
                subtitle = dummySubtitle
            )
        )
    }
}
