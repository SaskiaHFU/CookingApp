package com.saskiahfu.hfu.cookingapp.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem.Cart
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.MainBottomNavigation
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.MainNavigationGraph


@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val totalCount by viewModel.bindUi().observeAsState(0)
    MainScreenUI(totalCount)
}

@Composable
private fun MainScreenUI(totalCount: Int) {

    val font = MaterialTheme.typography.h2
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route


            TopAppBar(
                title = {
                    Row(
                        modifier.padding(start = 30.dp)
                    ) {

                        when (currentRoute) {
                            Cart.routeName -> Text(//TODO für alle anlegen
                                text = stringResource(Cart.title), style = font
                            )
                            BottomNavigationItem.Recipes.routeName -> Text(
                                text = stringResource(BottomNavigationItem.Recipes.title), style = font
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.background

            )
        },
        bottomBar = { MainBottomNavigation(navController, totalCount) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavigationGraph(navController)
        }
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreenUI(42)
}
