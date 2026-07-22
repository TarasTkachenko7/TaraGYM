package com.example.targym.ui.theme

import com.example.targym.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
)

val InterFont = FontFamily(
    Font(resId = R.font.inter_regular, weight = FontWeight.Normal),
    Font(resId = R.font.inter_medium, weight = FontWeight.Medium),
    Font(resId = R.font.inter_light, weight = FontWeight.Light),
    Font(resId = R.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.inter_bold, weight = FontWeight.Bold)
)

val HintTextStyle = TextStyle(
    fontFamily = InterFont,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    color = SecondText,
    textAlign = TextAlign.Center,
    lineHeight = 20.sp
)

val BigHintTextStyle = TextStyle(
    fontSize = 24.sp,
    color = FirstText,
    fontFamily = InterFont,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 30.sp
)

val ButtonsTextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = InterFont,
    fontWeight = FontWeight.SemiBold,
    letterSpacing = 0.5.sp
)

val DialogBoxTextStyle = TextStyle(
    color = FirstText,
    fontFamily = InterFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp
)
