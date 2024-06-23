@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.screen.mainfeatures.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.navigation.BookPage
import com.androidalliance.mynilam.navigation.RecordPage
import com.androidalliance.mynilam.navigation.ReviewPage
import com.androidalliance.mynilam.ui.components.RatingStar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun ReviewScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    // User State
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: ""
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ){
            Text(
                text = "Book Review",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            Button(
                onClick = { navController.navigate(RecordPage.AddForm.route) }
            ) {
                Text(text = "Add")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        // LazyColumn For Listing
        val recordList = listOf(
            "Reincarnated as a Hunter",
            "I Regressed Back Before World End"
        )
        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            items(recordList){
                    recordTitle ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    onClick = {
                        navController.navigate(
                            BookPage.Detail.route + "/${recordTitle}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .width(110.dp)
                                .fillMaxHeight(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                        ){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Placeholder")
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 13.dp, start = 11.dp, end = 16.dp, bottom = 20.dp),
                        ) {
                            Text(
                                text = recordTitle,
                                style = MaterialTheme.typography.titleMedium,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 2.dp, bottom = 5.dp)
                            )
                            Text(
                                text = "Reviewer : Name",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 2.dp, bottom = 5.dp),
                            )
                            RatingStar(rating = 3)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
