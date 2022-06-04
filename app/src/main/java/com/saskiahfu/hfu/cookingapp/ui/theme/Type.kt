package com.saskiahfu.hfu.cookingapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.saskiahfu.hfu.cookingapp.R


//Declare Fonts
val HeadingsFont = FontFamily(
    Font(R.font.cormorantgaramond_bold, FontWeight.Bold),
    Font(R.font.cormorantgaramond_semibold, FontWeight.SemiBold),
    Font(R.font.cormorantgaramond_semibold, FontWeight.Normal)
)

val TextFont = FontFamily(
    Font(R.font.spartan_semibold, FontWeight.SemiBold),
    Font(R.font.spartan_regular, FontWeight.Normal),
)


// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = HeadingsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 70.sp,
        color = Color.Black
    ),

    h2 = TextStyle(
        fontFamily = HeadingsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        color = Color.Black
    ),

    h3 = TextStyle(
        fontFamily = HeadingsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        color = Color.Black
    ),

    h4 = TextStyle(
        fontFamily = TextFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        color = Color.Black
    ),

    h5 = TextStyle(
        fontFamily = TextFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp,
        color = Color.Black
    ),

    body1 = TextStyle(
        fontFamily = TextFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.Black
    ),

    body2 = TextStyle(
        fontFamily = TextFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = Color.Black
    ),

    button = TextStyle(
        fontFamily = TextFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = Color.Black
    )

)
