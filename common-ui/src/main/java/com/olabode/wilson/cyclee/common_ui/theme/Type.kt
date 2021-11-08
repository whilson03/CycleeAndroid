package com.olabode.wilson.cyclee.common_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.common_ui.R

val inter = FontFamily(
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_extra_light, FontWeight.ExtraLight),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
    Font(R.font.inter_black, FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = inter,

    subtitle1 = TextStyle(
        fontFamily = inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    subtitle2 = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    body1 = TextStyle(
        fontFamily = inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    body2 = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    button = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    caption = TextStyle(
        fontFamily = inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    overline = TextStyle(
        fontFamily = inter,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    ),

    h5 = TextStyle(
        fontFamily = inter,
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold
    ),

)
