package com.saskiahfu.hfu.cookingapp.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    HomeScreenUI()
}

@Composable
private fun HomeScreenUI() {
    val textBox = MaterialTheme.typography.body2
    val smallBoxWidth = 160.dp
    val smallBoxHeight = 125.dp
    val bigBoxWidth = 160.dp
    val bigBoxHeight = 205.dp
    val bottomPadding = PaddingValues(
        bottom = 35.dp
    )

    Column(
        modifier
            .fillMaxWidth(),
    ) {

// Content
        Box(
            modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .padding(contentPadding)
            ) {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(345.dp)
                ) {
                    Row(
                        modifier.align(Alignment.TopStart)
                    ) {
                        Box(
                            modifier
                                .clip(
                                    RoundedCornerShape(30.dp)
                                )
                                .background(MaterialTheme.colors.secondary)
                                .size(width = bigBoxWidth, height = bigBoxHeight)
                                .padding(bottomPadding),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = "Meal Plan",
                                style = textBox
                            )
                        }
                    }
                    Row(
                        modifier.align(Alignment.TopEnd)
                    ) {
                        Box(
                            modifier
                                .clip(
                                    RoundedCornerShape(30.dp)
                                )
                                .background(MaterialTheme.colors.secondary)
                                .size(width = smallBoxWidth, height = smallBoxHeight)
                                .padding(bottomPadding),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = "Shopping Cart",
                                style = textBox
                            )
                        }
                    }
                    Row(
                        modifier.align(Alignment.BottomStart)
                    ) {
                        Box(
                            modifier
                                .clip(
                                    RoundedCornerShape(30.dp)
                                )
                                .background(MaterialTheme.colors.secondary)
                                .size(width = smallBoxWidth, height = smallBoxHeight)
                                .padding(bottomPadding),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = "Try Out",
                                style = textBox
                            )
                        }
                    }
                    Row(
                        modifier.align(Alignment.BottomEnd)
                    ) {
                        Box(
                            modifier
                                .clip(
                                    RoundedCornerShape(30.dp)
                                )
                                .background(MaterialTheme.colors.secondary)
                                .size(width = bigBoxWidth, height = bigBoxHeight)
                                .padding(bottomPadding),
                            contentAlignment = Alignment.BottomCenter
                        ) {

                            Text(
                                text = "Season Calender",
                                style = textBox
                            )
                        }
                    }
                }
                Box(
                    modifier
                        .height(smallBoxHeight)
                        .padding(top = 15.dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(30.dp)
                        )
                        .background(MaterialTheme.colors.secondary)

                        .padding(bottomPadding),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Row(
                        modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "View all Recipes",
                            style = textBox
                        )
                    }
                }
            }
        }
    }
}