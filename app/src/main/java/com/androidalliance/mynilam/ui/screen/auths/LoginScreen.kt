package com.androidalliance.mynilam.ui.screen.auths

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidalliance.mynilam.ui.components.AuthTextField
import com.androidalliance.mynilam.ui.components.HeaderText
import com.androidalliance.mynilam.ui.theme.MyNilamNilamManagementTheme

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(bottom = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    MyNilamNilamManagementTheme {
        LoginScreen()
    }
}