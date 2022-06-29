package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.single


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

@Composable
fun SingleRecipeScreen(
    viewModel: SingleRecipeViewModel = viewModel(),
    navController: NavController
) {
    val recipe by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    SingleRecipeScreenUI(recipe, navController)
}

@Composable
private fun SingleRecipeScreenUI(
    recipes: List<RecipeUI>,
    navController: NavController
) {


    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var sourceName by remember { mutableStateOf("") }
    var sourceUri by remember { mutableStateOf("") }

    var linkClicked by remember { mutableStateOf(false) }

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

//Go Back Icon
    val menuIconPadding = PaddingValues(
        start = 15.dp,
        top = 20.dp,
    )

    Button(
        onClick = {
            println("click")
            navController.navigate("recipes")
        },
        modifier.padding(menuIconPadding),
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Go Back",
            modifier.size(40.dp)
        )
    }


//Content
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

            .padding(contentPadding)
    ) {
        Column(
            modifier
                .padding(recipeImgPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = img,
                contentDescription = name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
            )

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
//
//                ) {
//                Icon(
//                    Icons.Default.Add,
//                    contentDescription = "Upload Image",
//                    tint = MaterialTheme.colors.background,
//                )
//            }
        } //TODO img anzeigen

// Content
        Box(
            modifier
                .padding(contentPadding)
                .fillMaxHeight()
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
    }
}

//        val scrollState = rememberLazyListState()
//        LazyColumn(state = scrollState) {
//            println("in Col")
////            val recipe = recipes.filter {
////                it.id.value == singleRecipeID
////            }
//
//            items(recipes) { recipe ->
//                Box() {
//                    println("in box2")
//                    RecipeSingleItem(recipe)
//                }
//            }
//        }





