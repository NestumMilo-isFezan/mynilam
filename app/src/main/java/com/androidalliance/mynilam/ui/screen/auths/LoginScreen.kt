package com.androidalliance.mynilam.ui.screen.auths

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.components.AuthTextField
import com.androidalliance.mynilam.ui.components.HeaderText
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.LoginEvent
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

val defaultPadding = 16.dp
val itemSpacing = 12.dp

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val state = viewModel.state
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // To observe validation behaviours
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect{
                event ->
            when(event){
                is UserViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Welcome",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(MainScreen.Main.route){
                        popUpTo(MainScreen.Login.route){
                            inclusive = true
                        }
                    }
                }
                is UserViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Fail, No existed user",
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
            text = "Login",
            modifier = Modifier
                .padding(bottom = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        AuthTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.username,
            onValueChange = {
                viewModel.onLoginEvent(LoginEvent.UsernameChanged(it))
            },
            labelText = "Email",
            leadingIcon = Icons.Default.Person,
            isError = state.usernameError != null
        )
        AuthTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = {
                viewModel.onLoginEvent(LoginEvent.PasswordChanged(it))
            },
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            isError = state.passwordError != null
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        Button(
            onClick = {
                // Handle login
                viewModel.onLoginEvent(LoginEvent.Submit)

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(itemSpacing))
        TextButton(
            onClick = {
                navController.navigate(MainScreen.Register.route)
            }
        ) {
            Text("Don't Have an Account? Create Now!")
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    MyNilamNilamManagementTheme {
//        LoginScreen(navController)
//    }
//}

