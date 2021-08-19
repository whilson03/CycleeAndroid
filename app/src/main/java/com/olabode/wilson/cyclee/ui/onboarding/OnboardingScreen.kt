package com.olabode.wilson.cyclee.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.olabode.wilson.cyclee.data.OnBoardingData
import com.olabode.wilson.cyclee.ui.theme.Orange600

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen() {
    val pageContents = remember { OnBoardingData.getOnBoardingContentList() }
    val pagerState = rememberPagerState(pageCount = pageContents.size)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        HorizontalPager(state = pagerState) { page ->
            OnBoardingPageItem(data = pageContents[page])
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Orange600,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(32.dp),
            )

            OnBoardingNavigation {
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview
fun PreviewOnBoardingScreen() {
    OnBoardingScreen()
}
