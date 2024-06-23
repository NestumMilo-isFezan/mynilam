@file:OptIn(ExperimentalMaterial3Api::class)
package com.androidalliance.mynilam.ui.screen.mainfeatures.home.statistics

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun StatisticScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Statistic Screen")
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(270.dp)
                    .padding(10.dp),
                onClick = {
                    // navigate dengan popUpTo itu mesti sama, haishh
                    navController.navigate(MainScreen.Home.route){
                        popUpTo(MainScreen.Home.route){
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Go Back")
            }
        }
    }
}