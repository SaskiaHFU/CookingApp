package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.single


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.recipeImgPadding
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipeUI
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.singleRecipeID
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanUI
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanViewModel

@Composable
fun SingleRecipeScreen(
    viewModel: SingleRecipeViewModel = viewModel(),
    viewModelMeal: MealplanViewModel = viewModel(),
    navController: NavController
) {
    val recipe by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    val meals by viewModelMeal.bindUi(LocalContext.current).observeAsState(emptyList())
    SingleRecipeScreenUI(recipe, viewModelMeal::onUpdateMealplan, meals, navController)
}

@Composable
private fun SingleRecipeScreenUI(
    recipes: List<RecipeUI>,
    onUpdateMealplan: (day: String, bfName: String, luName: String, diName: String) -> Unit,
    meals: List<MealplanUI>,
    navController: NavController
) {


    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var sourceName by remember { mutableStateOf("") }
    var sourceUri by remember { mutableStateOf("") }

    //    Mealplan
    var day by remember { mutableStateOf("") }
    var bfName by remember { mutableStateOf("") }
    var luName by remember { mutableStateOf("") }
    var diName by remember { mutableStateOf("") }

    var bfSelected by remember { mutableStateOf(false) }
    var luSelected by remember { mutableStateOf(false) }
    var diSelected by remember { mutableStateOf(false) }


    var linkClicked by remember { mutableStateOf(false) }
    var expandColumn by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }

    val scrollState = rememberLazyListState()

    recipes.filter {
        it.id.value == singleRecipeID
    }.map { recipe ->
        name = recipe.name
        img = recipe.img_url.toString()
        category = recipe.category
        ingredients = recipe.ingredients
        steps = recipe.steps
        sourceName = recipe.sourceName
        sourceUri = recipe.sourceUri
    }


    if (linkClicked) {
        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(sourceUri)) }
        context.startActivity(intent)
    }

    if (showPopup) {
        AlertDialog(
            onDismissRequest = {
                showPopup = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPopup = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                )
                { Text(text = "OK") }
            },

            title = {
                Text(
                    text = "Great!",
                    style = MaterialTheme.typography.body1,
                )
            },
            text = {
                Text(
                    text = "Your Mealplan has been updated",
                    style = MaterialTheme.typography.body1,
                )
            }
        )
    }

//Go Back Icon
    val menuIconPadding = PaddingValues(
        start = 15.dp,
        top = 20.dp,
    )

//    Column() {
//        IconButton(
//            onClick = {
//                println("click")
//                navController.navigate("recipes")
//            },
//            modifier.padding(menuIconPadding),
//        ) {
//            Icon(
//                Icons.Filled.ArrowBack,
//                contentDescription = "Go Back",
//                modifier.size(40.dp)
//            )
//        }
//    }


//Content
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier
                .padding(recipeImgPadding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = img,
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(end = 8.dp),
            )

        }


        Box(
            modifier
                .padding(contentPadding)
        ) {
            Column {
//Add Titel
                Row() {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Spacer(modifier.height(40.dp))

//Category
                Column() {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier.height(45.dp))
// Zutaten
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier.height(10.dp))
                Text(
                    text = ingredients,
                    style = MaterialTheme.typography.body1,
                )
                Spacer(modifier.height(45.dp))

// Steps
                Text(
                    text = "Steps",
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier.height(10.dp))
                Text(
                    text = steps,
                    style = MaterialTheme.typography.body1,
                )

                Spacer(modifier.height(45.dp))

//Add to Meal Week
                Text(
                    text = stringResource(R.string.recipe_add_meal_week),
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier.height(10.dp))

                LazyRow(
                    state = scrollState,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(meals) { meal ->
                        Column() {

                            Row() {
                                TextButton(
                                    onClick = {
                                        expandColumn = true
                                        day = meal.day
                                        println(expandColumn)

                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor =
//                                        if (daySelected.value) MaterialTheme.colors.surface
//                                        else
                                        MaterialTheme.colors.background
                                    ),
                                ) {

                                    Text(
                                        text = meal.day,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }

                            if (expandColumn) {
                                Column() {
                                    TextButton(
                                        onClick = {
                                            bfSelected = true
                                            bfName = if (bfSelected) {
                                                name
                                            } else {
                                                meal.bfName
                                            }

                                            println(meal.bfName)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor =
//            if (clicked) MaterialTheme.colors.surface
//            else
                                            MaterialTheme.colors.background
                                        ),
                                    ) {
                                        Text(
                                            text = "Breakfast",
                                            style = MaterialTheme.typography.body1
                                        )
                                    }
                                    TextButton(
                                        onClick = {
                                            luSelected = true
                                            luName = if (luSelected) {
                                                name
                                            } else {
                                                meal.luName
                                            }
                                            println(luName)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.background
                                        ),
                                    ) {
                                        Text(
                                            text = "Lunch",
                                            style = MaterialTheme.typography.body1
                                        )
                                    }
                                    TextButton(
                                        onClick = {
                                            diSelected = true
                                            diName = if (diSelected) {
                                                name
                                            } else {
                                                meal.diName
                                            }
                                            println(meal.diName)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.background
                                        ),
                                    ) {
                                        Text(
                                            text = "Dinner",
                                            style = MaterialTheme.typography.body1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
//Source
                Text(
                    text = "Source",
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sourceName,
                        style = MaterialTheme.typography.body1,
                    )
                    IconButton(
                        onClick = {
                            linkClicked = true

                        },

                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_outline_link_24),
                            contentDescription = "Follow Link",
                        )
                    }
                }
                Spacer(modifier.height(30.dp))
            }
        }


        //Save Button
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    onUpdateMealplan(day, bfName, luName, diName)
                    showPopup = true
                },
                enabled = name.isNotBlank(),
                modifier = Modifier
                    .size(height = 65.dp, width = 205.dp)
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
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            ) {
                Text(
                    text = stringResource(R.string.save_changes),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}








