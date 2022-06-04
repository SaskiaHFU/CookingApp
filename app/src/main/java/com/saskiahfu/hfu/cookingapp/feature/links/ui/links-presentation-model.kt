package com.saskiahfu.hfu.cookingapp.feature.links.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

class LinkUI(
    val id: String,
    val name: String,
    val category: String,
    val uri: String
)

val allLinks = arrayOf(
    LinkUI(
        id = "link_number",
        name = "Name | Source",
        category = allLinkCategories.first().name,
        uri = "https://..." //TODO safe as link
    ),
    LinkUI(
        id = "link_number2",
        name = "Name | Source",
        category = allLinkCategories[1].name,
        uri = "https://..." //TODO safe as link
    )
)

@Composable
fun PrintLinks() {

    for (link in allLinks) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = link.name,
                style = MaterialTheme.typography.body1,
            )

            IconButton(
                onClick = {
                    //text.value = "Link clicked. "
                    // if clicked go to link.uri
                },

                ) {
                Icon(
                    Icons.Filled.Add, //TODO Change Icon
                    contentDescription = "Go to Source",
                    modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = {
                    //TODO Code Delete Link action
                    //text.value = "Link clicked. "

                },

                ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete Link",
                    modifier.size(24.dp)
                )
            }
        }

    }

}
