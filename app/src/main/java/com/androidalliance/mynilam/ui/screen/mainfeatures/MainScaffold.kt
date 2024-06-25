package com.androidalliance.mynilam.ui.screen.mainfeatures

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.androidalliance.mynilam.navigation.homeNavGraph
import com.androidalliance.mynilam.ui.components.HomeBottomBar
import com.androidalliance.mynilam.ui.components.HomeTopBar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun MainScaffold(
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel,
){
    val showTopBar by viewModel.sharedShowTopAppBar.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            if(showTopBar)
            {
                HomeTopBar()
            }
            else{
                null
            }
        },
        bottomBar = { HomeBottomBar(navController)}
    ){
        innerPadding -> val modifier = Modifier.padding(innerPadding)
        homeNavGraph(navController, viewModel)
    }
}