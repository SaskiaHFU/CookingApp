package com.saskiahfu.hfu.cookingapp.feature.calendar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.R


data class Vegetable(
    val id: String,
    val name: String,
    val img: Int,
    val month: String

)

val allVegetables = listOf(

    Vegetable(
        id = "carrot",
        name = "Carrot",
        img = R.drawable.bowl_png,
        month = "January"
    )
)

@Composable
fun printVegetableLayout(vegetable: Vegetable) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(vegetable.img),
            contentDescription = vegetable.name,
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopStart,
            modifier = Modifier
                .width(75.dp),
        )
        Text(
            text = vegetable.name,
            style = MaterialTheme.typography.body1,

            )
    }
}