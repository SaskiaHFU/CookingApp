package com.saskiahfu.hfu.cookingapp.feature.cart.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import com.saskiahfu.hfu.cookingapp.feature.main.*

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel()
) {
    val cartItems by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    CartScreenUI(
        viewModel::onAddCartItem,
        viewModel::onDeleteCart,
        viewModel::onDeleteCartItem,
        cartItems
    )
}

@Composable
private fun CartScreenUI(
    onAddCartItem: (item: String) -> Unit,
    onDeleteCart: () -> Unit,
    onDeleteItemById: (CartItemId) -> Unit,
    items: List<CartItemUI>
) {
    val textStyle = MaterialTheme.typography.body2
    val textButton = MaterialTheme.typography.button

    var newItem by remember { mutableStateOf("") }

    var addItem by remember { mutableStateOf(false) }
    var sent by remember { mutableStateOf(false) }


    Column(
        modifier
            .fillMaxWidth()
    ) {

//Call Header
//        menuIcon()
//        pageDirection("Shopping Cart")

        Box(
            modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .padding(contentPadding)
            ) {
                //TODO List items
                val scrollState = rememberLazyListState()
                LazyColumn(
                    state = scrollState,
                ) {
                    items(items) { item ->
                        CartItem(item, onDeleteItemById)
                    }
                }
                Spacer(modifier.height(20.dp))

                Row() {
                    if (addItem) {
                        sent = false
                        TextField(
                            value = newItem,
                            onValueChange = { newItem = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(R.string.type_here)) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        )
                        IconButton(
                            onClick = {
                                onAddCartItem(newItem)
                                newItem = ""
                                sent = true
                            },
                        ) {
                            Row() {
                                Icon(
                                    painter = painterResource(R.drawable.ic_baseline_check_24),
                                    stringResource(R.string.add_item)
                                )
                            }
                        }
                    }

                    if (sent) {
                        addItem = false
                    }
                }

                Row() {
                    IconButton(
                        onClick = {
                            addItem = true
                        },
                    ) {
                        Row() {
                            Icon(
                                Icons.Default.Add,
                                stringResource(R.string.add_item)
                            )
                            Spacer(modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.add_one_more),
                                style = textStyle
                            )
                        }
                    }
                }


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
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onDeleteCart()
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
                            text = stringResource(R.string.clear),
                            style = textButton,
                        )
                    }
                }
            }
        }
    }
}

