package com.olabode.wilson.cyclee.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.R

private val Candara = FontFamily(
    Font(R.font.candara_regular),
    Font(R.font.candara_bold),
    Font(R.font.candara_bold_italic),
    Font(R.font.candara_italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Candara,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    button = TextStyle(
        fontFamily = Candara,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Candara,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    h5 = TextStyle(
        fontFamily = Candara,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),

)
