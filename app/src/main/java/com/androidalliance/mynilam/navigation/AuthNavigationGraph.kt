package com.androidalliance.mynilam.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.androidalliance.mynilam.ui.screen.auths.LoginScreen
import com.androidalliance.mynilam.ui.screen.auths.RegisterScreen
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.splash.SplashScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    viewModel: UserViewModel
){
    navigation(
        startDestination = MainScreen.Splash.route,
        route = MainScreen.Auth.route // this stands for route of the navigation graph
    ){
        composable(
            route = MainScreen.Splash.route,
        ){
            SplashScreen(navController)
        }
        composable(
            route = MainScreen.Login.route,
        ){
            LoginScreen(navController, viewModel)
        }
        composable(
            route = MainScreen.Register.route
        ){
            RegisterScreen(navController, viewModel)
        }
    }
}