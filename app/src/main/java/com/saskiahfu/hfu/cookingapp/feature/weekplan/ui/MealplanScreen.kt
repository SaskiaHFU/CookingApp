package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.menuIcon
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.pageDirection


@Composable
fun MealplanScreen(viewModel: MealplanViewModel = viewModel()) {
    val meals by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    MealplanScreenUI(meals)
}

@Composable
private fun MealplanScreenUI(meals: List<MealplanUI>) {
    Column(
    ) {

        //Call Header
//        menuIcon()
//        pageDirection("My Week")

        //Content
        Box(
            modifier
                .padding(contentPadding)
        ) {
            Column {
                val scrollState = rememberLazyListState()
                LazyColumn(state = scrollState) {
                    items(meals) { meal ->
                        MealplanItem(meal)
                    }
                }
            }
        }
    }

    // Button Clear
    Column(
        modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = {
                    //TODO Code Delete clicked Items Button action

                },
                modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp
                        )
                    ),
                contentPadding = PaddingValues(
                    top = 24.dp,
                    bottom = 24.dp,
                    start = 40.dp,
                    end = 24.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
            ) {
                Text(
                    text = stringResource(R.string.clear),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}
