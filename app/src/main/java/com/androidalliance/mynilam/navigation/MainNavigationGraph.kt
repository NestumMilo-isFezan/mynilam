package com.androidalliance.mynilam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidalliance.mynilam.MyNilamApplication
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.BookScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.home.HomeScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.home.statistics.StatisticScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.ProfileFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.ProfileScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel.ProfileViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.RecordScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel.RecordViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewDetailScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewFormScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.ReviewScreen
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel.ReviewViewModel
import com.androidalliance.mynilam.ui.screen.viewModelFactory

@Composable
fun homeNavGraph(
    navController: NavHostController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
){
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()

    // BookVM
    val bookViewModel = viewModel<BookViewModel>(
        factory = viewModelFactory {
            BookViewModel(
                bookRepository = (MyNilamApplication.appModule.readingMaterialRepository)
            )
        }
    )
    val bookState = bookViewModel.sharedBookState.collectAsStateWithLifecycle()

    // RecordVM
    val recordViewModel = viewModel<RecordViewModel>(
        factory = viewModelFactory {
            RecordViewModel(
                recordRepository = (MyNilamApplication.appModule.recordRepository),
                bookRepository = (MyNilamApplication.appModule.readingMaterialRepository)
            )
        }
    )

    // ReviewVM
    val reviewViewModel = viewModel<ReviewViewModel>(
        factory = viewModelFactory {
            ReviewViewModel(
                reviewRepository = (MyNilamApplication.appModule.reviewRepository),
                bookRepository = (MyNilamApplication.appModule.readingMaterialRepository)
            )
        }
    )

    // ProfileVM
    val profileViewModel = viewModel<ProfileViewModel>(
        factory = viewModelFactory {
            ProfileViewModel(
                profileRepository = (MyNilamApplication.appModule.profileRepository)
            )
        }
    )



    NavHost(
        navController = navController,
        startDestination = MainScreen.Home.route,
        route = MainScreen.Main.route // this stands for route of the navigation graph
    ){
        // Home
        composable(
            route = MainScreen.Home.route,
        ){
            HomeScreen(navController, viewModel, modifier)
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
            RecordScreen(navController, viewModel, bookViewModel ,recordViewModel, modifier)
        }
        // Record -> Add Form
        composable(
            route = RecordPage.AddForm.route
        ){
            RecordFormScreen(navController, "add", recordViewModel, bookViewModel, viewModel)
        }
        // Record -> Detail
        composable(
            route = RecordPage.Detail.route,
        ){
            RecordDetailScreen(navController, bookViewModel, recordViewModel, viewModel)

        }
        // Record -> Details -> Edit
        composable(
            route = RecordPage.EditForm.route
        ){
            RecordFormScreen(navController, "edit", recordViewModel, bookViewModel, viewModel)
        }


        ////////////////////////
        // Book
        composable(
            route = MainScreen.Book.route
        ){
            BookScreen(navController, viewModel, bookViewModel , modifier)
        }
        // Book -> Add Form
        composable(
            route = BookPage.AddForm.route
        ){
            BookFormScreen(navController, "add", bookViewModel, viewModel)
        }
        // Book -> Detail
        composable(
            route = BookPage.Detail.route
        ){
            // Check if the book name, ada ke tidak
            BookDetailScreen(navController, bookViewModel, viewModel)

        }
        // Book -> Details -> Edit
        composable(
            route = BookPage.EditForm.route
        ){
            BookFormScreen(navController, "edit", bookViewModel, viewModel)
        }


        ///////////////////////
        // Review
        composable(
            route = MainScreen.Review.route
        ){
            ReviewScreen(navController, viewModel, reviewViewModel, bookViewModel, modifier)
        }
        // Review -> Add Form
        composable(
            route = ReviewPage.AddForm.route
        ){
            ReviewFormScreen(navController, "add", reviewViewModel, bookViewModel, viewModel)
        }
        // Review -> Detail
        composable(
            route = ReviewPage.Detail.route
        ){
            ReviewDetailScreen(navController, reviewViewModel, bookViewModel, viewModel)
        }
        // Review -> Details -> Edit
        composable(
            route = ReviewPage.EditForm.route
        ){
            ReviewFormScreen(navController, "edit", reviewViewModel, bookViewModel, viewModel)
        }


        // Profile
        composable(
            route = MainScreen.Profile.route
        ){
            ProfileScreen(navController, viewModel, profileViewModel, modifier)
        }
        composable(
            route = ProfilePage.EditForm.route
        ){
            ProfileFormScreen(navController, profileViewModel, viewModel)
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