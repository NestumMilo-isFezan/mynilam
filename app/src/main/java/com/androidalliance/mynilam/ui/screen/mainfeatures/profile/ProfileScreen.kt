package com.androidalliance.mynilam.ui.screen.mainfeatures.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.ProfilePage
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    // User State
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val username by remember {
        derivedStateOf {
            userState.value?.username ?: ""
        }
    }
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: ""
        }
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "My Profile")
            Button(
                onClick = { navController.navigate(ProfilePage.EditForm.route + "/$userId") }
            ) {
                Text(text = "Edit")
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(170.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Text(text = "Your Name is : $username")
            }
        }
    }
}