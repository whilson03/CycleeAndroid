package com.olabode.wilson.cyclee.feature_onboarding.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.olabode.wilson.cyclee.common_ui.theme.Grey600
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_onboarding.data.OnBoardingData
import com.olabode.wilson.cyclee.feature_onboarding.domain.OnBoardingItem

@ExperimentalPagerApi
@Composable
fun OnBoardingPageItem(
    modifier: Modifier = Modifier,
    data: OnBoardingItem
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = data.imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(0.40f)
                .offset(y = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            OnBoardingSubsection(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                title = data.title,
                subtitle = data.subtitle
            )
        }
    }
}

@Composable
fun OnBoardingSubsection(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            color = Orange600,
            maxLines = 1,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis

        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.caption,
            color = Grey600,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun OnBoardingNavigation(
    modifier: Modifier = Modifier,
    onNext: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        FloatingActionButton(
            modifier = Modifier.padding(16.dp),
            backgroundColor = Orange600,
            onClick = { onNext() }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                tint = Color.White,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomSection() {
    OnBoardingNavigation(onNext = {})
}

@Preview
@Composable
fun PreviewSubsection() {
    OnBoardingSubsection(
        title = OnBoardingData.dummytitle,
        subtitle = OnBoardingData.dummySubtitle
    )
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewOnBoardingPage() {
    OnBoardingPageItem(
        modifier = Modifier,
        OnBoardingData.getOnBoardingContentList()[0]
    )
}
