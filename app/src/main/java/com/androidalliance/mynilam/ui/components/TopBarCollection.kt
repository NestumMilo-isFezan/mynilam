@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun HomeTopBar() {
    TopAppBar(
        title = { Text("MyNilam") }
    )
}

@Composable
fun HomeTopBarWithBack(title: String) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Search")
            }
        }
    )
}
