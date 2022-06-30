package com.saskiahfu.hfu.cookingapp.feature.main.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.*
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem.Cart


@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
//    val totalCount by viewModel.bindUi().observeAsState(0)
    MainScreenUI()
}

@Composable
private fun MainScreenUI() {

    val font = MaterialTheme.typography.h2
    var navigated by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            TopAppBar(
                title = {
                    when (currentRoute) {
                        Cart.routeName -> Text(
                            text = stringResource(Cart.title), style = font
                        )
                        BottomNavigationItem.Recipes.routeName -> Text(
                            text = stringResource(BottomNavigationItem.Recipes.title),
                            style = font
                        )
                        BottomNavigationItem.Links.routeName -> Text(
                            text = stringResource(BottomNavigationItem.Links.title),
                            style = font
                        )
                        BottomNavigationItem.Week.routeName -> Text(
                            text = stringResource(BottomNavigationItem.Week.title),
                            style = font
                        )
                        BottomNavigationItem.Home.routeName -> Text(
                            text = stringResource(BottomNavigationItem.Home.title),
                            style = font
                        )
                        BottomNavigationItem.Profile.routeName -> Text(
                            text = stringResource(BottomNavigationItem.Profile.title),
                            style = font
                        )
                        BottomNavigationItem.AddRecipe.routeName -> Text(
                            text = stringResource(BottomNavigationItem.AddRecipe.title),
                            style = font
                        )
                        BottomNavigationItem.RecipeSingle.routeName -> IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                                  },
                                        modifier.padding(start = 15.dp, top = 20.dp),
                                    ) {
                                        Icon(
                                            Icons.Filled.ArrowBack,
                                            contentDescription = "Go Back",
                                            modifier.size(40.dp)
                                        )
                                    }


                    }
                },

                navigationIcon = {
                    if (BottomNavigationItem.RecipeSingle.routeName != currentRoute) {
                        MainTopNavigation(navController)
                    }
                },
                backgroundColor = MaterialTheme.colors.background,

                )
        },
        bottomBar = { MainBottomNavigation(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavigationGraph(navController)
        }
    }
}
