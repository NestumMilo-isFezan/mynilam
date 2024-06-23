package com.androidalliance.mynilam.ui.screen.mainfeatures.book

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.ui.components.FormInfoField

@Composable
fun BookFormScreen(
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
        when (formMode) {
            "add" -> formTitle = "Add Book"
            "edit" -> formTitle = "Edit Book"
        }
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
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier.padding(20.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (formMode) {
                    "add" -> {

                    }
                    "edit" -> {

                    }
                }


                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Book Name",
                    leadingIcon = Icons.Default.Book,
                )
                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Author",
                    leadingIcon = Icons.Default.Person,
                )
                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Publication",
                    leadingIcon = Icons.Default.Print,
                )
                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Reading Material Type",
                    leadingIcon = Icons.Default.Archive,
                )
                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Genre",
                    leadingIcon = Icons.Default.Dashboard,
                )
                FormInfoField(
                    value = bookName,
                    onValueChange = { /*ToDo*/ },
                    labelText = "Publication Year",
                    leadingIcon = Icons.Default.CalendarMonth,
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
fun BookFormScreenPreview() {
    BookFormScreen(navController = NavHostController(LocalContext.current), formMode = "add", bookName = "")
}