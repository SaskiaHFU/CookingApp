package com.saskiahfu.hfu.cookingapp.feature.recipes.ui
//
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
//import com.saskiahfu.hfu.cookingapp.feature.main.goBackIcon
//import com.saskiahfu.hfu.cookingapp.feature.main.modifier
//import com.saskiahfu.hfu.cookingapp.feature.main.recipeImgPadding
//import kotlin.reflect.KFunction8
//
//@Composable
//fun AddRecipeScreen(viewModel: AddRecipeViewModel = viewModel()) {
//    AddRecipeScreenUI(viewModel::onAddRecipe)
//}
//
//@Composable
//private fun AddRecipeScreenUI(
//    onAddRecipe: (String, String, String, String, String, String) -> Unit
//) {
//    var name by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var img by remember { mutableStateOf("") }
////    var ingredients by remember { mutableStateOf("") }
////    var steps by remember { mutableStateOf("") }
//    var sourceName by remember { mutableStateOf("") }
//    var sourceUri by remember { mutableStateOf("") }
//
//
////Go Back Icon
//    goBackIcon()
//
////Image
//    Column(
//        modifier
//            .fillMaxWidth()
//            .verticalScroll(rememberScrollState())
//            .fillMaxHeight(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier
//                .padding(recipeImgPadding)
//                .fillMaxHeight(),
//        ) {
//            Button(
//                onClick = {
//                    //TODO code upload Img
//                },
//                modifier
//                    .size(height = 150.dp, width = 150.dp)
//                    .clip(shape = RoundedCornerShape(30.dp)),
//                contentPadding = PaddingValues(
//                    top = 50.dp,
//                    bottom = 50.dp,
//                    start = 50.dp,
//                    end = 50.dp
//                ),
//                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
//            ) {
//                Icon(
//                    Icons.Default.Add,
//                    contentDescription = "Upload Image",
//                    tint = MaterialTheme.colors.background,
//                )
//            }
//        }
//
//// Content
//        Box(
//            modifier
//                .padding(contentPadding)
//                .fillMaxHeight()
//        ) {
//            Column {
////Add Titel
//                Row() {
//                    Text(
//                        text = "Add Title",
//                        style = MaterialTheme.typography.body1,
//                    )
//                    TextField(
//                        value = name,
//                        onValueChange = { name = it },
//                        singleLine = true,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = MaterialTheme.colors.background
//                        )
//                    )
//                }
//                Spacer(modifier.height(40.dp))
////Category
//
//                Column() {
//                    Text(
//                        text = "Choose Category",
//                        style = MaterialTheme.typography.h5,
//                        overflow = TextOverflow.Ellipsis,
//                    )
//                    Spacer(modifier.width(10.dp))
//
//                    Row(
//                        modifier.horizontalScroll(rememberScrollState()),
//                        horizontalArrangement = Arrangement.spacedBy(10.dp)
//                    ) {
//                        //TODO make responsive on categories number; create function to call Button Layout
//                        TextButton(
//                            onClick = {
//                                category = "Breakfast"
//                            },
//                        ) {
//                            Text(
//                                text = "Breakfast",
//                                style = MaterialTheme.typography.body1,
//                            )
//                        }
//                        TextButton(
//                            onClick = {
//                                //TODO Code Choose Category Button action
//                            },
//                        ) {
//                            Text(
//                                text = "Lunch",
//                                style = MaterialTheme.typography.body1,
//                            )
//                        }
//                        TextButton(
//                            onClick = {
//                                //TODO Code Choose Category Button action
//                            },
//                        ) {
//                            Text(
//                                text = "Dinner",
//                                style = MaterialTheme.typography.body1,
//                            )
//                        }
//                        TextButton(
//                            onClick = {
//                                //TODO Code Choose Category Button action
//                            },
//                        ) {
//                            Text(
//                                text = "Kids",
//                                style = MaterialTheme.typography.body1,
//                            )
//                        }
//                        TextButton(
//                            onClick = {
//                                //TODO Code Choose Category Button action
//                            },
//                        ) {
//                            Text(
//                                text = "Drinks",
//                                style = MaterialTheme.typography.body1,
//                            )
//                        }
//                    }
//                }
//                Spacer(modifier.height(45.dp))
//// Zutaten
//                Text(
//                    text = "Add Ingredients",
//                    style = MaterialTheme.typography.h5,
//                )
//                Spacer(modifier.height(10.dp))
////                TextField(
////                    value = ingredients,
////                    onValueChange = { ingredients = it },
////                    singleLine = true,
////                    colors = TextFieldDefaults.textFieldColors(
////                        backgroundColor = MaterialTheme.colors.background
////                    )
////                )
//                Spacer(modifier.height(45.dp))
//
//// Steps
//                Text(
//                    text = "Steps",
//                    style = MaterialTheme.typography.h5,
//                )
//                Spacer(modifier.height(10.dp))
////                TextField(
////                    value = steps,
////                    onValueChange = { steps = it },
////                    singleLine = true,
////                    colors = TextFieldDefaults.textFieldColors(
////                        backgroundColor = MaterialTheme.colors.background
////                    )
////                )
//                Spacer(modifier.height(45.dp))
//
////Add to Meal Week
//
////Source
//                Text(
//                    text = "Add Source Link",
//                    style = MaterialTheme.typography.h5,
//                )
//
//                Spacer(modifier.height(10.dp))
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    //TODO code source link form
//                    Row() {
//                        Text(
//                            text = "Title",
//                            style = MaterialTheme.typography.body1,
//                        )
//                        TextField(
//                            value = sourceName,
//                            onValueChange = { sourceName = it },
//                            singleLine = true,
//                            colors = TextFieldDefaults.textFieldColors(
//                                backgroundColor = MaterialTheme.colors.background
//                            )
//                        )
//                    }
//                    Row() {
//                        Text(
//                            text = "Link",
//                            style = MaterialTheme.typography.body1,
//                        )
//                        TextField(
//                            value = sourceUri,
//                            onValueChange = { sourceUri = it },
//                            singleLine = true,
//                            colors = TextFieldDefaults.textFieldColors(
//                                backgroundColor = MaterialTheme.colors.background
//                            )
//                        )
//                    }
//
//                }
//                Spacer(modifier.height(30.dp))
//            }
//        }
//
////Save Button
//        Row(
//            modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            Button(
//                onClick = {
//                    //TODO Code Save Button action
//                },
//                modifier
//                    .size(height = 65.dp, width = 205.dp)
//                    .clip(
//                        shape = RoundedCornerShape(
//                            topStart = 30.dp,
//                            bottomStart = 30.dp
//                        )
//                    ),
//                contentPadding = PaddingValues(
//                    top = 24.dp,
//                    bottom = 24.dp,
//                    start = 24.dp,
//                    end = 40.dp
//                ),
//                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
//            ) {
//                Text(
//                    text = "Save",
//                    style = MaterialTheme.typography.button,
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun AddRecipeScreen_Preview() {
//    AddRecipeScreenUI { _, _, _, _, _, _ -> }
//}