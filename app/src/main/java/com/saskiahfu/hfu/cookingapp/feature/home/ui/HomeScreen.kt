package com.saskiahfu.hfu.cookingapp.feature.home.ui
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.saskiahfu.hfu.cookingapp.R
//import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
//import com.saskiahfu.hfu.cookingapp.feature.main.modifier
//import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipesViewModel
//import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoriesViewModel
//import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryUI
//
//
//@Composable
//fun HomeScreen(
//    viewModel: RecipeCategoriesViewModel,
//    navController: NavController
//) {
//    val categories by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
//    HomeScreenUI(categories, viewModel::onAddCategory)
//}
//
//@Composable
//private fun HomeScreenUI(
//    recipeCategory: List<RecipeCategoryUI>,
//    onAddCategory: (String) -> Unit,
//) {
//
//    val textBox = MaterialTheme.typography.body2
//    val smallBoxWidth = 160.dp
//    val smallBoxHeight = 125.dp
//    val bigBoxWidth = 160.dp
//    val bigBoxHeight = 205.dp
//    val bottomPadding = PaddingValues(
//        bottom = 35.dp
//    )
//    var count = 0
//    var showPopup by remember { mutableStateOf(false) }
//    var categoryName by remember { mutableStateOf("") }
//
//    if (showPopup) {
//        AlertDialog(
//            onDismissRequest = {
//                showPopup = false
//            },
//            dismissButton = {
//                Button(
//                    onClick = {
//                        showPopup = false
//                    },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
//                )
//                { Text("Don't save", modifier.background(MaterialTheme.colors.background)) }
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        onAddCategory(categoryName)
//                        showPopup = false
//                    },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
//                )
//                { Text(text = "Save") }
//            },
//
//            title = {
//                Text(
//                    text = "What's your category? ",
//                    style = MaterialTheme.typography.body1,
//                )
//            },
//            text = {
//                TextField(
//                    value = categoryName,
//                    onValueChange = { categoryName = it },
//                    singleLine = true,
//                    placeholder = { Text(stringResource(R.string.type_here)) },
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = MaterialTheme.colors.surface
//                    )
//                )
//            }
//        )
//    }
//
//    Column(
//        modifier
//            .fillMaxWidth(),
//    ) {
//        Box(
//            modifier.fillMaxHeight()
//        ) {
//            Column(
//                modifier
//                    .padding(contentPadding)
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Box(
//                    modifier
//                        .height(smallBoxHeight)
//                        .padding(top = 15.dp)
//                        .fillMaxWidth()
//                        .clip(
//                            RoundedCornerShape(30.dp)
//                        )
//                        .background(MaterialTheme.colors.secondary)
//
//                        .padding(bottomPadding),
//                    contentAlignment = Alignment.BottomCenter,
//                ) {
//                    Button(
//                        onClick = { showPopup = true },
//                        modifier
//                            .height(smallBoxHeight)
//                            .padding(top = 15.dp)
//                            .fillMaxWidth()
//                            .clip(
//                                RoundedCornerShape(30.dp)
//                            )
//                            .background(MaterialTheme.colors.secondary)
//
//                            .padding(bottomPadding),
//                    ) {
//
//                        Row(
//                            modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//
//                            Text(
//                                text = stringResource(R.string.recipe_add_new_cat),
//                                style = textBox
//                            )
//                        }
//                    }
//
//                }
//
//                Box(
//                    modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                ) {
//                    recipeCategory.map { item ->
//                        count += 1
//
//                        when (count) {
//                            1 -> {
//                                Row(
//                                    modifier.align(Alignment.TopStart)
//                                ) {
//                                    Box(
//                                        modifier
//                                            .clip(
//                                                RoundedCornerShape(30.dp)
//                                            )
//                                            .background(MaterialTheme.colors.secondary)
//                                            .size(width = bigBoxWidth, height = bigBoxHeight)
//                                            .padding(bottomPadding),
//                                        contentAlignment = Alignment.BottomCenter
//                                    ) {
//                                        Text(
//                                            text = item.name,
//                                            style = textBox
//                                        )
//                                    }
//                                }
//
//                            }
//                            2 -> {
//                                Row(
//                                    modifier.align(Alignment.TopEnd)
//                                ) {
//                                    Box(
//                                        modifier
//                                            .clip(
//                                                RoundedCornerShape(30.dp)
//                                            )
//                                            .background(MaterialTheme.colors.secondary)
//                                            .size(
//                                                width = smallBoxWidth, height = smallBoxHeight
//                                            )
//                                            .padding(bottomPadding),
//                                        contentAlignment = Alignment.BottomCenter
//                                    ) {
//                                        Text(
//                                            text = item.name,
//                                            style = textBox
//                                        )
//                                    }
//                                }
//                            }
//                            3 -> {
//                                Row(
//                                    modifier.align(Alignment.BottomStart)
//                                ) {
//                                    Box(
//                                        modifier
//                                            .clip(
//                                                RoundedCornerShape(30.dp)
//                                            )
//                                            .background(MaterialTheme.colors.secondary)
//                                            .size(width = smallBoxWidth, height = smallBoxHeight)
//                                            .padding(bottomPadding),
//                                        contentAlignment = Alignment.BottomCenter
//                                    ) {
//                                        Text(
//                                            text = item.name,
//                                            style = textBox
//                                        )
//                                    }
//                                }
//                            }
//                            4 -> {
//                                Row(
//                                    modifier.align(Alignment.BottomEnd)
//                                ) {
//                                    Box(
//                                        modifier
//                                            .clip(
//                                                RoundedCornerShape(30.dp)
//                                            )
//                                            .background(MaterialTheme.colors.secondary)
//                                            .size(
//                                                width = bigBoxWidth, height = bigBoxHeight
//                                            )
//                                            .padding(bottomPadding),
//                                        contentAlignment = Alignment.BottomCenter
//                                    ) {
//                                        Text(
//                                            text = item.name,
//                                            style = textBox
//                                        )
//                                    }
//                                }
//                                count = 0
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}