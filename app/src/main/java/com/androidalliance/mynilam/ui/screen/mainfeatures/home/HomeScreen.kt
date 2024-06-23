@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.screen.mainfeatures.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val username by remember {
        derivedStateOf {
            userState.value?.username ?: ""
        }
    }
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: ""
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen")
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(270.dp)
                .padding(10.dp),
            onClick = {
                navController.navigate(MainScreen.Statistic.route)
            }
        ) {
            Text(text = "Hello $username")
        }
    }
}