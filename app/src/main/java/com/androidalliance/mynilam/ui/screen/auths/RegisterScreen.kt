package com.androidalliance.mynilam.ui.screen.auths

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.MyNilamApplication
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.components.AuthTextField
import com.androidalliance.mynilam.ui.components.HeaderText
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.RegistrationEvent
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.viewModelFactory


@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val state = viewModel.state
    val context = LocalContext.current

    // To observe validation behaviours
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect{
            event ->
            when(event){
                is UserViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Success",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Login.route){
                        popUpTo(MainScreen.Login.route){
                            inclusive = true
                        }
                    }
                }
                is UserViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Sorry, either email or username is existed in database!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(itemSpacing))
        HeaderText(
            text = "Register",
            modifier = Modifier
                .padding(bottom = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        AuthTextField(
            value = state.email,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            labelText = "Email",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardType = KeyboardType.Email,

        )
        if(state.emailError != null){
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error
            )
        }
        AuthTextField(
            value = state.username,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationEvent.UsernameChanged(it))
            },
            isError = state.usernameError != null,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
            )
        if(state.usernameError != null){
            Text(
                text = state.usernameError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = { viewModel.onRegisterEvent(RegistrationEvent.PasswordChanged(it)) },
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            isError = state.passwordError != null,
            visualTransformation = PasswordVisualTransformation()
        )
        if(state.passwordError != null){
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        AuthTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.confirmPassword,
            onValueChange = { viewModel.onRegisterEvent(RegistrationEvent.ConfirmPasswordChanged(state.password, it)) },
            labelText = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            isError = state.passwordError != null,
            visualTransformation = PasswordVisualTransformation()
        )
        if(state.confirmPasswordError != null){
            Text(
                text = state.confirmPasswordError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Spacer(modifier = Modifier.height(itemSpacing))
        Button(
            onClick = {
                // Handle register
                viewModel.onRegisterEvent(RegistrationEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Register")
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun RegisterScreenPreview() {
//    MyNilamNilamManagementTheme {
//        RegisterScreen(navController)
//    }
//}

