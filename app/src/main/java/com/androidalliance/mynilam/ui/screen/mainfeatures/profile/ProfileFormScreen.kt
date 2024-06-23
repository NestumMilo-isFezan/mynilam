package com.androidalliance.mynilam.ui.screen.mainfeatures.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileFormScreen(
    navController: NavHostController,
    profileId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Edit Profile $profileId")
        Spacer(modifier = Modifier.height(20.dp))
//        TextField(value = profileId, onValueChange = {})
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*ToDo*/ }
        ) {
            Text(text = "Save Changes")
        }
    }
}