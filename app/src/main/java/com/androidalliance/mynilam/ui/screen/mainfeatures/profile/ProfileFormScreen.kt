package com.androidalliance.mynilam.ui.screen.mainfeatures.profile

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.navigation.ProfilePage
import com.androidalliance.mynilam.ui.components.FormInfoField
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.FormBookEvent
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel.ProfileFormEvent
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel.ProfileViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel.FormRecordEvent

@Composable
fun ProfileFormScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    viewModel: UserViewModel
) {
    viewModel.hideTopAppBar()
    val state = profileViewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        profileViewModel.validationEvents.collect{
                event ->
            when(event){
                is ProfileViewModel.ValidationEvent.Updated -> {
                    Toast.makeText(
                        context,
                        "Your profile is updated, brads!",
                        Toast.LENGTH_LONG
                    )
                    navController.navigate(MainScreen.Profile.route){
                        popUpTo(ProfilePage.EditForm.route){
                            inclusive = true
                        }
                    }
                }
                is ProfileViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Failed, 404 again?",
                        Toast.LENGTH_LONG
                    )
                }
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val formTitle = "Edit Profile"
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
            ) {
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
                            value = state.firstName,
                            onValueChange = {
                                profileViewModel.onCreateEvent(
                                    ProfileFormEvent.FirstNameChanged(it)
                                )
                            },
                            labelText = "Book Name",
                            leadingIcon = Icons.Default.Book,
                            isError = state.firstNameError != null
                        )
                        FormInfoField(
                            value = state.lastName,
                            onValueChange = {
                                profileViewModel.onCreateEvent(
                                    ProfileFormEvent.LastNameChanged(it)
                                )
                            },
                            labelText = "Book Name",
                            leadingIcon = Icons.Default.Book,
                            isError = state.lastNameError != null
                        )
                        OutlinedTextField(
                            modifier = Modifier.height(200.dp),
                            value = state.motto,
                            onValueChange = { profileViewModel.onCreateEvent(ProfileFormEvent.MottoChanged(it)) },
                            isError = false,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            shape = RoundedCornerShape(12.dp)
                        )

                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            profileViewModel.onCreateEvent(ProfileFormEvent.Submit)
                        },
                        modifier = Modifier
                    ) {
                        Text("Update")
                    }

                }
            }
        }


    }
}