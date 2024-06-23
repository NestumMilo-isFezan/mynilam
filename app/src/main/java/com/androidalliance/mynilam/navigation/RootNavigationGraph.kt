package com.androidalliance.mynilam.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidalliance.mynilam.MyNilamApplication
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.MainScaffold
import com.androidalliance.mynilam.ui.screen.viewModelFactory

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {
    val viewModel = viewModel<UserViewModel>(
        factory = viewModelFactory {
            UserViewModel(
                userRepository = (MyNilamApplication.appModule.userRepository)
            )
        }
    )
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = MainScreen.Auth.route, // This tell's the navHost which screen to show when the application is launched
        route = MainScreen.Root.route
    ) {
        authNavGraph(navController, viewModel)
        composable(
            route = MainScreen.Main.route,
        ) {
            MainScaffold(viewModel = viewModel)
        }
    }
}