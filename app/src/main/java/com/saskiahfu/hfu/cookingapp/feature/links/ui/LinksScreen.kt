package com.saskiahfu.hfu.cookingapp.feature.links.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.feature.main.boxPadding
import com.saskiahfu.hfu.cookingapp.feature.main.menuIcon
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.pageDirection

@Composable
fun LinksScreen(viewModel: LinksViewModel = viewModel()) {
    LinksScreenUI()
}

@Composable
private fun LinksScreenUI() {
    Column(
        modifier
            .fillMaxWidth(),
    ) {

//Call Header
        menuIcon()
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            pageDirection("Try out")
            CreateAddCatButton(text = "Add Category") {

                //CreateCatOverlay()
            }
        }


//Print Categories
        PrintListCategories(allLinkCategories.first())

//Print Links
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(boxPadding)

        ) {
            PrintActiveCategory()
            PrintLinks()
        }
    }
}

@Preview
@Composable
fun LinksScreen_Preview() {
    LinksScreenUI()
}