package com.androidalliance.mynilam.ui.screen.mainfeatures.review

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.navigation.RecordPage
import com.androidalliance.mynilam.navigation.ReviewPage
import com.androidalliance.mynilam.ui.components.FormDropdownMenu
import com.androidalliance.mynilam.ui.components.RatingStar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel.FormRecordEvent
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel.RecordViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel.ReviewFormEvent
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel.ReviewViewModel

@Composable
fun ReviewFormScreen(
    navController: NavHostController,
    formMode: String,
    reviewViewModel: ReviewViewModel,
    bookViewModel: BookViewModel,
    viewModel: UserViewModel
) {
    // State List
    viewModel.hideTopAppBar()
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val booksList by bookViewModel.sharedBooks.collectAsState(initial = emptyList())
    val formState = reviewViewModel.state
    val context = LocalContext.current

    // To observe validation behaviours
    LaunchedEffect(key1 = context) {
        reviewViewModel.validationEvents.collect{
                event ->
            when(event){
                is ReviewViewModel.ValidationEvent.Inserted -> {
                    Toast.makeText(
                        context,
                        "New Review Added",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Review.route){
                        popUpTo(ReviewPage.AddForm.route){
                            inclusive = true
                        }
                    }
                }
                is ReviewViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Not sure, 404 I guess?",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ReviewViewModel.ValidationEvent.Update -> {
                    Toast.makeText(
                        context,
                        "Operation Edit : Done, Bro!",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Review.route){
                        popUpTo(ReviewPage.EditForm.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title Bar
        var formTitle = ""

        when (formMode) {
            "add" -> formTitle = "Give"
            "edit" -> formTitle = "Edit"
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$formTitle Rating",
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
        }

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(booksList.isNotEmpty()){
                    FormDropdownMenu(
                        selectedValue = reviewViewModel.state.selectedBook,
                        options = booksList,
                        onValueChangeEvent = {
                            reviewViewModel.onCreateEvent(ReviewFormEvent.BookChanged(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Book Rating",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                RatingStar(
                    modifier = Modifier.size(40.dp),
                    rating = formState.rating,
                    onRatingChanged = { reviewViewModel.onCreateEvent(ReviewFormEvent.RatingChanged(it)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Record Summary",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.height(200.dp),
                    value = formState.review,
                    onValueChange = { reviewViewModel.onCreateEvent(ReviewFormEvent.ReviewChanged(it)) },
                    isError = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                val userId by remember {
                    derivedStateOf {
                        userState.value?.uid ?: 0
                    }
                }
                if(formMode == "add"){
                    Button(
                        onClick = {
                            reviewViewModel.state = reviewViewModel.state.copy(userId = userId)
                            reviewViewModel.onCreateEvent(ReviewFormEvent.Submit)
                        },
                        modifier = Modifier
                    ){
                        Text("Submit $userId")
                    }
                }
                else{
                    Button(
                        onClick = {
                            reviewViewModel.state = reviewViewModel.state.copy(userId = userId)
                            reviewViewModel.onCreateEvent(ReviewFormEvent.Update)
                        },
                        modifier = Modifier
                    ){
                        Text("Update")
                    }
                }
            }

        }
    }
}
