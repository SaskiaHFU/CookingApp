package com.saskiahfu.hfu.cookingapp.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun menuIcon() {
    val menuIconPadding = PaddingValues(
        start = 15.dp,
        top = 20.dp,
    )
    Row(
        modifier.padding(menuIconPadding)
    ) {
        IconButton(
            onClick = {
                //text.value = "Menu clicked. "
            },
        ) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Open Menu",
                modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun goBackIcon() {
    val menuIconPadding = PaddingValues(
        start = 15.dp,
        top = 20.dp,
    )
    Row(
        modifier.padding(menuIconPadding)
    ) {
        IconButton(
            onClick = {
                //text.value = "Back clicked. "
            },
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Go Back",
                modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun pageDirection(page: String) { //TODO make scrollable till menu icon - seperate functions
    val startPadding = PaddingValues(
        start = 30.dp,
        top = 15.dp,
        end = 30.dp,
    )

    Row(
        modifier.padding(startPadding)
    ) {
        Text(
            text = page,
            style = MaterialTheme.typography.h2,
        )
    }
}
