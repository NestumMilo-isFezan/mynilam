package com.androidalliance.mynilam.ui.screen.mainfeatures

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.androidalliance.mynilam.navigation.homeNavGraph
import com.androidalliance.mynilam.ui.components.HomeBottomBar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun MainScaffold(
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel
){
    Scaffold(
        topBar = {  },
        bottomBar = { HomeBottomBar(navController)}
    ){
        paddingValues -> var modifier = Modifier.padding(paddingValues)
        homeNavGraph(navController, viewModel)
    }
}