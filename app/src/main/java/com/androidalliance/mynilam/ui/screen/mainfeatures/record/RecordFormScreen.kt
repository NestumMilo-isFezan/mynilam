package com.androidalliance.mynilam.ui.screen.mainfeatures.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.ui.components.FormDropdownMenu

@Composable
fun RecordFormScreen(
    navController: NavHostController,
    formMode: String,
    recordName: String
) {
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
            "add" -> formTitle = "Add"
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
                text = "$formTitle Record",
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
                when (formMode) {
                    "add" -> {

                    }
                    "edit" -> {

                    }
                }

                FormDropdownMenu()
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Record Summary",
                    modifier = Modifier.align(Alignment.Start).padding(bottom = 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = Modifier.height(200.dp),
                    value = recordName,
                    onValueChange = { /*ToDo*/ },
                    isError = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { /*ToDo*/ }
                ) {
                    when(formMode){
                        "add" -> Text(text = "Add Book")
                        "edit" -> Text(text = "Edit Book")
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RecordFormScreenPreview() {
    RecordFormScreen(navController = NavHostController(LocalContext.current), formMode = "add", recordName = "")
}