package com.androidalliance.mynilam.ui.screen.mainfeatures.book

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.BookPage
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.components.FormInfoField
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.FormBookEvent

@Composable
fun BookFormScreen(
    navController: NavHostController,
    formMode: String,
    bookViewModel: BookViewModel,
    viewModel: UserViewModel
) {
    // User State
    viewModel.hideTopAppBar()
    val state = bookViewModel.state
    val context = LocalContext.current

// To observe validation behaviours
    LaunchedEffect(key1 = context) {
       bookViewModel.validationEvents.collect{
                event ->
            when(event){
                is BookViewModel.ValidationEvent.Inserted -> {
                    Toast.makeText(
                        context,
                        "New Reading Materials Added",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Book.route){
                        popUpTo(BookPage.AddForm.route){
                            inclusive = true
                        }
                    }
                }
                is BookViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Failed, Existed Book",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is BookViewModel.ValidationEvent.Update -> {
                    Toast.makeText(
                        context,
                        "Operation Edit : Done, Bro!",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Book.route){
                        popUpTo(BookPage.EditForm.route){
                            inclusive = true
                        }
                    }
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title Bar
        var formTitle = ""
        when (formMode) {
            "add" -> formTitle = "Add Book"
            "edit" -> formTitle = "Edit Book"
        }
        Column(
            modifier = Modifier.fillMaxHeight(0.7f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formTitle,
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
            }

            Card(
                modifier = Modifier
                    .padding(10.dp)
            ){
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        FormInfoField(
                            value = state.title,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.TitleChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Book Name",
                            leadingIcon = Icons.Default.Book,
                            isError = state.titleError != null
                        )
                        FormInfoField(
                            value = state.author,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.AuthorChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Author",
                            leadingIcon = Icons.Default.Person,
                            isError = state.authorError != null
                        )
                        FormInfoField(
                            value = state.publication,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.PublishedChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Publisher",
                            leadingIcon = Icons.Default.Print,
                            isError = state.publicationError != null
                        )
                        FormInfoField(
                            value = state.type,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.TypeChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Reading Material Type",
                            leadingIcon = Icons.Default.Archive,
                            isError = state.typeError != null
                        )
                        FormInfoField(
                            value = state.genre,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.GenreChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Genre",
                            leadingIcon = Icons.Default.Dashboard,
                            isError = state.genreError != null
                        )
                        FormInfoField(
                            value = state.publicationYear,
                            onValueChange = {
                                bookViewModel.onCreateEvent(
                                    FormBookEvent.YearChanged(
                                        it
                                    )
                                )
                            },
                            labelText = "Publication Year",
                            leadingIcon = Icons.Default.CalendarMonth,
                            isError = state.publicationYearError != null
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    if (formMode == "edit") {
                        Button(
                            onClick = {
                                bookViewModel.onCreateEvent(FormBookEvent.Update)
                            },
                            modifier = Modifier
                        ){
                            Text("Update")
                        }
                    } else if (formMode == "add") {
                        Button(
                            onClick = {
                                bookViewModel.onCreateEvent(FormBookEvent.Submit)
                            },
                            modifier = Modifier
                        ){
                            Text("Submit")
                        }
                    }
                }
            }
        }
    }
}
