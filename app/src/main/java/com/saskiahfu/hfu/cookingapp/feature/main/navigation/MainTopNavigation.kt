package com.saskiahfu.hfu.cookingapp.feature.main.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainTopNavigation(navController: NavController) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    )
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOf(
            BottomNavigationItem.Profile,
            BottomNavigationItem.AddRecipe

        ).forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.routeName,
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = navItem.routeName
                    )
                },
                onClick = {
                    navController.navigate(navItem.routeName) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                unselectedContentColor = Color.Black,
                selectedContentColor = MaterialTheme.colors.surface,
                )
        }
    }
}