package com.androidalliance.mynilam.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.Person2
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainScreen(val route : String ){
    object Root : MainScreen(route = "root_screen")
    object Auth : MainScreen(route = "auth_screen")
    object Splash : MainScreen(route = "splash_screen")
    object Login : MainScreen(route = "login_screen")
    object Register : MainScreen(route = "register_screen")
    object Main : MainScreen(route = "main_screen")
    object Home : MainScreen(route = "home_screen")
    object Statistic : MainScreen(route = "statistic_screen")
    object Record : MainScreen(route = "record_screen")
    object Book : MainScreen(route = "book_screen")
    object Review : MainScreen(route = "review_screen")
    object Profile : MainScreen(route = "profile_screen")
}


sealed class RecordPage(val route : String){
    object AddForm : RecordPage(route = "edit_record_form_page")
    object EditForm : RecordPage(route = "add_record_form_page")
    object Detail : RecordPage(route = "record_detail_page")
}

sealed class BookPage(val route : String){
    object AddForm : BookPage(route = "add_book_form_page")
    object EditForm : BookPage(route = "edit_book_form_page")
    object Detail : BookPage(route = "book_detail_page")
}

sealed class ReviewPage(val route : String){
    object AddForm : ReviewPage(route = "add_review_form_page")
    object EditForm : ReviewPage(route = "edit_review_form_page")
    object Detail : ReviewPage(route = "review_detail_page")
}

sealed class ProfilePage(val route : String){
    object EditForm : ProfilePage(route = "profile_form_page")
}



sealed class BottomTabItems(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home : BottomTabItems(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Record : BottomTabItems(
        route = "record_screen",
        title = "RECORD",
        icon = Icons.Default.NoteAlt
    )
    object Book : BottomTabItems(
        route = "book_screen",
        title = "BOOK",
        icon = Icons.Default.Book
    )
    object Review : BottomTabItems(
        route = "review_screen",
        title = "REVIEW",
        icon = Icons.Default.ChatBubbleOutline
    )
    object Profile : BottomTabItems(
        route = "profile_screen",
        title = "PROFILE",
        icon = Icons.Default.Person2
    )

}