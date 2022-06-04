package com.saskiahfu.hfu.cookingapp.feature.cart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

@Composable
fun ShoppingCartItem(product: ShoppingCartScreenProductUI) {

    Icon(
        Icons.Filled.Check,
        contentDescription = "Check",
        modifier.size(30.dp)
    )

    Text(
        text = product.name,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )


//    Card(
//        elevation = 3.dp,
//        modifier = Modifier.padding(8.dp),
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            AsyncImage(
//                model = product.iconUrl ?: product.icon,
//                contentDescription = product.name,
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(end = 8.dp),
//            )
//            Column(
//                modifier = Modifier
//                    .padding(start = 8.dp)
//                    .weight(1f),
//            ) {
//                Text(
//                    text = product.name,
//                    fontWeight = FontWeight.Bold,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                )
//                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                    Text(
//                        text = LocalContext.current.resources.getQuantityString(R.plurals.cart_item_count, product.amount, product.amount),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                        style = MaterialTheme.typography.subtitle1,
//                    )
//                }
//            }
//            Text(
//                text = NumberFormat.getCurrencyInstance().format(product.totalCost),
//                fontWeight = FontWeight.Light,
//                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
}

@SuppressLint("ResourceType")
@Preview
@Composable
fun ShoppingCartItem_Preview() {
    ShoppingCartItem(
        ShoppingCartScreenProductUI(
            id = ProductId("foo"),
            name = "spezi",
            description = "description foobar",
            amount = 17,
            totalCost = 42.0,
            icon = 1,
            iconUrl = null,
        )
    )
}
