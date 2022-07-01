package com.saskiahfu.hfu.cookingapp.feature.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//Global Variables

val modifier = Modifier

val REQUEST_CODE = 100

val contentPadding = PaddingValues(
    start = 30.dp,
    top = 45.dp,
    end = 30.dp,
)

val categoryPadding = PaddingValues(
    start = 30.dp,
    top = 10.dp,
    end = 30.dp,
)

val containerPadding = PaddingValues(
    start = 30.dp,
    end = 30.dp,
)

val boxPadding = PaddingValues(
    start = 30.dp,
    end = 30.dp,
    top = 30.dp
)

val recipeImgPadding = PaddingValues(
    start = 30.dp,
    top = 70.dp,
    end = 30.dp,
    bottom = 30.dp
)

val bottomBarPadding = PaddingValues(
    top = 30.dp,
    bottom = 50.dp
)

val signInPadding = PaddingValues(
    start = 30.dp,
    top = 30.dp,
    end = 30.dp,
)



val days =
    mutableListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

val months =
    mutableListOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )



