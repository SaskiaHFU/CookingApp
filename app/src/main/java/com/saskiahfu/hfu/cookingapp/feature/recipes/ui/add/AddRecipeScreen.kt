package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.*
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.AddRecipeCategoryItem
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryUI
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanUI
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanViewModel
import org.apache.commons.io.FileUtils
import java.io.File


private fun createFileFromUri(name: String, uri: Uri, context: Context): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file =
            File.createTempFile(
                "${name}_${System.currentTimeMillis()}",
                ".png",
                context.cacheDir
            )
        FileUtils.copyInputStreamToFile(stream, file)  // Use this one import org.apache.commons.io.FileUtils
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}



@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeViewModel = viewModel(),
    viewModelMeal: MealplanViewModel = viewModel(),
    navController: NavController
) { //TODO weiter nav gehen
    val recipeCategories by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    val meals by viewModelMeal.bindUi(LocalContext.current).observeAsState(emptyList())
    AddRecipeScreenUI(viewModel::onAddRecipe, recipeCategories, meals, navController)
}

@Composable
private fun AddRecipeScreenUI(
    onAddRecipe: (name: String, img: String, ingredients: String, steps: String, category: String, sourceName: String, sourceUri: String) -> Unit,
//    onUpdateMealplan: (mealName: String, time: String),
    recipeCategories: List<RecipeCategoryUI>,
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


    var showPopup by remember { mutableStateOf(false) }
    val catSelected = remember { mutableStateOf(false) }


//    Mealplan
    var day = remember { mutableStateOf("") }
    var bfName = remember { mutableStateOf("") }
    var luName = remember { mutableStateOf("") }
    var diName = remember { mutableStateOf("") }

    val daySelected = remember { mutableStateOf(false) }
    val timeSelected = remember { mutableStateOf(false) }


//    Image Folder
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        imageUri = uri
        imageUri?.let { createFileFromUri(name, it, context) }

        println("url: " + imageUri)
        // innerhalb von app irgendwo hinkopieren (zwspeichern
    }

    var showAddImgButton by remember { mutableStateOf(true) }

    val scrollState = rememberLazyListState()


//    Interface
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showPopup) {
            AlertDialog(
                onDismissRequest = {
                    showPopup = false
                    navController.navigate(BottomNavigationItem.Recipes.routeName)
                },
                confirmButton = {
                    TextButton(onClick = {
                        showPopup = false
                        navController.navigate(BottomNavigationItem.Recipes.routeName)
                    })
                    { Text(text = "OK") }
                },

                title = { Text(text = stringResource(R.string.recipe_popup_title)) },
                text = { Text(text = stringResource(R.string.recipe_popup_text)) }
            )

        }
//Image
        Row(
            modifier
                .padding(recipeImgPadding)
                .fillMaxHeight(),
        ) {
            if (showAddImgButton) {
                Button(
                    onClick = {
                        //TODO code upload Img
                        launcher.launch("image/*")
                        img = imageUri.toString()

                    },
                    modifier
                        .size(height = 150.dp, width = 150.dp)
                        .clip(shape = RoundedCornerShape(30.dp)),
                    contentPadding = PaddingValues(
                        top = 50.dp,
                        bottom = 50.dp,
                        start = 50.dp,
                        end = 50.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(R.string.recipe_upload_img),
                        tint = MaterialTheme.colors.background,
                    )
                }
            }

            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(400.dp)
                    )
                }
                showAddImgButton = false
                img = imageUri.toString()
            }
        }


// Content
        Box(
            modifier
                .padding(contentPadding)
                .fillMaxHeight()
        ) {
            Column {
//Add Titel
                Column() {
                    Text(
                        text = stringResource(R.string.recipe_add_title),
                        style = MaterialTheme.typography.body1,
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        placeholder = { Text(stringResource(R.string.type_here)) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )
                }
                Spacer(modifier.height(40.dp))
//Category
                Column() {
                    Text(
                        text = stringResource(R.string.recipe_add_category),
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier.width(10.dp))

                    LazyRow(
                        state = scrollState,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(recipeCategories) { cats ->
                            TextButton(
                                onClick = {
                                    catSelected.value = !catSelected.value
                                    category = cats.name
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    if (catSelected.value) MaterialTheme.colors.surface
                                    else MaterialTheme.colors.background
                                ),
                            ) {
                                AddRecipeCategoryItem(cats)
                            }
                        }
                    }
                }



                Spacer(modifier.height(45.dp))
// Zutaten
                Column() {
                    Text(
                        text = stringResource(R.string.recipe_add_ingredients),
                        style = MaterialTheme.typography.h5,
                    )
                    Spacer(modifier.height(10.dp))
                    TextField(
                        value = ingredients,
                        onValueChange = { ingredients = it },
                        maxLines = 5,
                        trailingIcon = {
                            Icon(Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear),
                                modifier = Modifier
                                    .clickable {
                                        ingredients = ""
                                    }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )
                }

                Spacer(modifier.height(45.dp))

// Steps
                Column() {
                    Text(
                        text = stringResource(R.string.recipe_add_steps),
                        style = MaterialTheme.typography.h5,
                    )
                    Spacer(modifier.height(10.dp))
                    TextField(
                        value = steps,
                        onValueChange = { steps = it },
                        maxLines = 5,
                        trailingIcon = {
                            Icon(Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear),
                                modifier = Modifier
                                    .clickable {
                                        steps = ""
                                    }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )
                }

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
                    var expandColumn = false

                    items(meals) { meal ->
                        Column() {

                            Row() {
                                TextButton(
                                    onClick = {
                                        expandColumn = true
                                        daySelected.value = !daySelected.value

//                                        get table eintrag where day = slected day
//                                day = me

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
//                            UpdateMealplanItem(item)
                                }
                            }

//                            if (expandColumn) {
                            Column() {
                                TextButton(
                                    onClick = {
                                        meal.bfName = name
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
                                        meal.luName = name
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
                                        meal.diName = name
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
//                            }


                        }

                    }
                }

//Source
                Text(
                    text = stringResource(R.string.recipe_add_link),
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier.height(10.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row() {
//                        Text(
//                            text = "Title",
//                            style = MaterialTheme.typography.body1,
//                        )
                        TextField(
                            value = sourceName,
                            onValueChange = { sourceName = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(R.string.recipe_add_source_name)) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        )
                    }
                    Row() {
//                        Text(
//                            text = "Link",
//                            style = MaterialTheme.typography.body1,
//                        )
                        TextField(
                            value = sourceUri,
                            onValueChange = { sourceUri = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(R.string.recipe_add_source_uri)) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
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
                    onAddRecipe(name, img, ingredients, steps, category, sourceName, sourceUri)
//                    onUpdateMealplan
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
                    text = stringResource(R.string.save_item),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}
