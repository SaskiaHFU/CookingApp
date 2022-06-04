package com.saskiahfu.hfu.cookingapp.feature.links.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saskiahfu.hfu.cookingapp.feature.main.containerPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

class LinkCategoryUI(
    val id: String,
    val name: String,
    val links: String,
    var status: Boolean
)

val allLinkCategories = arrayOf(
    LinkCategoryUI(
        id = "cat_1",
        name = "Breakfast",
        links = "all links", //TODO safe as list of recipes from database
        status = false
    ),
    LinkCategoryUI(
        id = "cat_2",
        name = "Lunch",
        links = "all links 2", //TODO safe as list of recipes from database
        status = false
    ),
    LinkCategoryUI(
        id = "cat_3",
        name = "Dinner",
        links = "all links 2", //TODO safe as list of recipes from database
        status = false
    ),

    )

@Composable
fun CreateAddCatButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            fontSize = 10.sp
        )
    }
}

@Composable
fun CreateCatButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun PrintListCategories(category: LinkCategoryUI) {
    var linkCatStatus = remember { mutableStateOf(false) }

    Box(
        modifier.padding(containerPadding)
    ) {
        Row(
            modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            for (category in allLinkCategories) {


                CreateCatButton(category.name + category.status) {
                    linkCatStatus.value = true
                    category.status = linkCatStatus.value

                    //TODO Just clicked on should have true property not all siblings

                    println("click" + category)
                    println(allLinkCategories.filter { it.status == true })
                }

            }
        }
        Spacer(modifier.height(25.dp))
    }
}

@Composable
fun PrintActiveCategory() {

    val target = allLinkCategories.find { it.status == true }
    if (target != null) {
        Row() {
            Text(
                text = target.name,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondary
            )
        }
    } else {

        Row() {
            Text(
                text = allLinkCategories.first().name,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondary
            )
        }

        allLinkCategories.first().status = true
    }
}