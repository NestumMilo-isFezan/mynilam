package com.androidalliance.mynilam.ui.screen.mainfeatures.review

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
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.ui.components.FormDropdownMenu
import com.androidalliance.mynilam.ui.components.RatingStar

@Composable
fun ReviewFormScreen(
    navController: NavHostController,
    formMode: String,
    bookName: String
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
        var rateBook by remember { mutableIntStateOf(0) }

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
                when (formMode) {
                    "add" -> {

                    }
                    "edit" -> {

                    }
                }

                FormDropdownMenu()
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
                    rating = rateBook,
                    onRatingChanged = { rateBook = it }
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
                    value = bookName,
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
                        "add" -> Text(text = "Rate Book")
                        "edit" -> Text(text = "Edit Rate")
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ReviewFormScreenPreview() {
    ReviewFormScreen(navController = NavHostController(LocalContext.current), formMode = "add", bookName = "")
}