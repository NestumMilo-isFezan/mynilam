package com.androidalliance.mynilam.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.home.HomeScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.home.statistics.StatisticScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.ProfileFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.ProfileScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewScreen

@Composable
fun homeNavGraph(
    navController: NavHostController,
    viewModel: UserViewModel
){
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    NavHost(
        navController = navController,
        startDestination = MainScreen.Home.route,
        route = MainScreen.Main.route // this stands for route of the navigation graph
    ){
        // Home
        composable(
            route = MainScreen.Home.route,
        ){
            HomeScreen(navController, viewModel)
        }
        // Home -> Statistic
        composable(
            route = MainScreen.Statistic.route
        ){
            StatisticScreen(navController, viewModel)
        }


        ///////////////////////////
        // Record
        composable(
            route = MainScreen.Record.route
        ){
            RecordScreen(navController, viewModel)
        }
        // Record -> Add Form
        composable(
            route = RecordPage.AddForm.route
        ){
            RecordFormScreen(navController, "add", "")
        }
        // Record -> Detail
        composable(
            route = RecordPage.Detail.route + "/{record_name}",
            arguments = listOf(
                navArgument("record_name"){
                    type = NavType.StringType
                }
            )
        ){
            // Check if the record name, ada ke tidak
            val recordName = it.arguments?.getString("record_name") ?: ""
            RecordDetailScreen(navController, recordName)

        }
        // Record -> Details -> Edit
        composable(
            route = RecordPage.EditForm.route + "/{record_name}",
            arguments = listOf(
                navArgument("record_name"){
                    type = NavType.StringType
                }
            )
        ){
            val recordName = it.arguments?.getString("record_name") ?: ""
            RecordFormScreen(navController, "edit", recordName)
        }


        ////////////////////////
        // Book
        composable(
            route = MainScreen.Book.route
        ){
            BookScreen(navController, viewModel)
        }
        // Book -> Add Form
        composable(
            route = BookPage.AddForm.route
        ){
            BookFormScreen(navController, "add", "")
        }
        // Book -> Detail
        composable(
            route = BookPage.Detail.route + "/{book_name}",
            arguments = listOf(
                navArgument("book_name"){
                    type = NavType.StringType
                }
            )
        ){
            // Check if the book name, ada ke tidak
            val bookName = it.arguments?.getString("book_name") ?: ""
            BookDetailScreen(navController, bookName)

        }
        // Book -> Details -> Edit
        composable(
            route = BookPage.EditForm.route + "/{book_name}",
            arguments = listOf(
                navArgument("book_name"){
                    type = NavType.StringType
                }
            )
        ){
            val recordName = it.arguments?.getString("book_name") ?: ""
            BookFormScreen(navController, "edit", recordName)
        }


        ///////////////////////
        // Review
        composable(
            route = MainScreen.Review.route
        ){
            ReviewScreen(navController, viewModel)
        }
        // Review -> Add Form
        composable(
            route = ReviewPage.AddForm.route
        ){
            ReviewFormScreen(navController, "add", "")
        }
        // Review -> Detail
        composable(
            route = ReviewPage.Detail.route + "/{review_name}",
            arguments = listOf(
                navArgument("review_name"){
                    type = NavType.StringType
                }
            )
        ){
            // Check if the review name, ada ke tidak
            val reviewName = it.arguments?.getString("review_name") ?: ""
            ReviewDetailScreen(navController, reviewName)
        }
        // Review -> Details -> Edit
        composable(
            route = ReviewPage.EditForm.route + "/{review_name}",
            arguments = listOf(
                navArgument("review_name"){
                    type = NavType.StringType
                }
            )
        ){
            val reviewName = it.arguments?.getString("review_name") ?: ""
            ReviewFormScreen(navController, "edit", reviewName)
        }


        // Profile
        composable(
            route = MainScreen.Profile.route
        ){
            ProfileScreen(navController, viewModel)
        }
        composable(
            route = ProfilePage.EditForm.route + "/{profile_id}",
            arguments = listOf(
                navArgument("profile_id"){
                    type = NavType.IntType
                }
            )
        ){
            val profileName = it.arguments?.getInt("profile_id") ?: 0
            ProfileFormScreen(navController, profileName)
        }
    }

}

// SharedViewModel
/*
* Benda pelik yang somehow dia boleh shared view model ke setiap navbar
*
*
*
*
*
*
* */