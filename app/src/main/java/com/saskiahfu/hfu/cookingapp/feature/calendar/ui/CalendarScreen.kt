package com.saskiahfu.hfu.cookingapp.feature.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.feature.main.*

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = viewModel()) {
    val products by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    CalendarScreenUI()
}

@Composable
private fun CalendarScreenUI() {
    Column(
        modifier
            .fillMaxWidth(),
    ) {

//Call Header
        menuIcon()
        pageDirection("Seasonal Guide")

//Call Months
        Box(
            modifier.padding(categoryPadding)
        ) {
            Row(
                modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                for (month in months) {
                    TextButton(
                        onClick = {
                            //TODO Code Choose Month Button action
                            //Give attribute active as class parameter
                        },
                        ) {
                        Text(
                            text = month,
                            style = MaterialTheme.typography.body1,
                            )
                    }
                }
            }
        }

        Box(
            modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(30.dp))
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
                    .padding(boxPadding)
            ) {

                Row(
                    modifier.fillMaxWidth()
                ) {
                    printVegetableLayout(allVegetables.first())
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarScreen_Preview() {
    CalendarScreenUI()
}