package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.androidalliance.mynilam.navigation.BottomTabItems

@Composable
fun HomeBottomBar(navController: NavController) {

    val screen = listOf(
        BottomTabItems.Home,
        BottomTabItems.Record,
        BottomTabItems.Book,
        BottomTabItems.Review,
        BottomTabItems.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottombarDestination = screen.any { it.route == currentDestination?.route }
    if (bottombarDestination){
        NavigationBar {
            screen.forEach{ pages->
                AddItems(
                    pages,
                    currentDestination,
                    navController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItems(screen: BottomTabItems, currentDestination: NavDestination?, navController: NavController) {
    NavigationBarItem(
        label = { Text(screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
        selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}