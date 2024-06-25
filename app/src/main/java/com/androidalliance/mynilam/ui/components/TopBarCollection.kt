@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.androidalliance.mynilam.R

@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        title = { Image(painter = painterResource(R.drawable.mobile_phone_nilam_nobg),
            contentDescription = "NILAM",
            modifier = Modifier
                .size(90.dp)
        ) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer,),
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
