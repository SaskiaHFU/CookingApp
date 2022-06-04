package com.saskiahfu.hfu.cookingapp.feature.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
fun ShoppingCartScreen(viewModel: ShoppingCartViewModel = viewModel()) {
    val cart by viewModel.bindUI(LocalContext.current).observeAsState(
        ShoppingCartScreenUI(emptyList(), 0.0)
    )
    ShoppingCartUI(cart, viewModel::onPayCart)
}

@Composable
private fun ShoppingCartUI(cart: ShoppingCartScreenUI, payCart: () -> Unit) {
    Column(
        modifier
            .fillMaxWidth(),
    ) {
        //Call Header
        menuIcon()
        pageDirection("Shopping Cart")
        Box(
            modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .padding(contentPadding)
            ) {
                //TODO List items
            }
            //Buttons Bottom
            Column(
                modifier
                    .fillMaxHeight()
                    .padding(bottomBarPadding),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            //TODO Code Delete clicked Items Button action
                        },
                        modifier
                            //.size(height = 40.dp, width = 115.dp)
                            .clip(shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)),
                        contentPadding = PaddingValues(
                            top = 24.dp,
                            bottom = 24.dp,
                            start = 40.dp,
                            end = 24.dp
                        ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    ) {
                        Text(
                            text = "Delete all clicked",
                            style = MaterialTheme.typography.button,
                        )
                    }
                    Button(
                        onClick = {
                            //TODO Code Clear List Items Button action
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
                            start = 24.dp,
                            end = 40.dp
                        ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    ) {
                        Text(
                            text = "Clear list",
                            style = MaterialTheme.typography.button,
                        )
                    }
                }
            }
        }
    }


//    val scrollState = rememberLazyListState()
//    Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        LazyColumn(
//            state = scrollState,
//            modifier = Modifier.weight(1f),
//        ) {
//            items(cart.products) { product ->
//                ShoppingCartItem(product)
//            }
//        }
//        Divider()
//        Button(
//            modifier = Modifier.padding(16.dp),
//            enabled = cart.totalCost > 0,
//            onClick = { payCart() },
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Icon(
//                        painterResource(R.drawable.ic_outline_shopping_cart_24),
//                        contentDescription = stringResource(R.string.cart_cta_pay),
//                        modifier = Modifier.padding(end = 8.dp),
//                    )
//                    Text(stringResource(R.string.cart_cta_pay))
//                }
//                Text(
//                    NumberFormat.getCurrencyInstance().format(cart.totalCost),
//                    fontWeight = FontWeight.Bold,
//                )
//            }
//        }
//    }
}

@Preview
@Composable
fun ShoppingCartScreen_Preview() {
    ShoppingCartUI(
        ShoppingCartScreenUI(
            products = emptyList(),
            totalCost = 42.13
        )
    ) {}
}
