@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.screen.mainfeatures.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.R
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.navigation.BookPage
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.navigation.RecordPage
import com.androidalliance.mynilam.ui.components.EmptyIconBg
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel.RecordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun RecordScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    bookViewModel: BookViewModel,
    recordViewModel: RecordViewModel,
    modifier: Modifier = Modifier
) {
    // User State
    viewModel.showTopAppBar()
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: 0
        }
    }

    LaunchedEffect(key1 = userId) {
        recordViewModel.findThisUserRecords(userId)
    }
    val summaryRecord = recordViewModel.sharedSummaryRecord.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(0.75f),
            verticalArrangement = Arrangement.Top
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ){
                Text(
                    text = "Book Summary Record",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
                IconButton(
                    onClick = {
                        recordViewModel.clearForm()
                        navController.navigate(RecordPage.AddForm.route)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Edit Profile"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            if(summaryRecord.value.isEmpty()){
                EmptyIconBg()
            }
            else{
                //             LazyColumn For Listing
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(summaryRecord.value){
                            recordItem ->

                        // Fetch Book Info
                        val bookInfo = remember {
                            mutableStateOf<ReadingMaterial?>(null)
                        }

                        LaunchedEffect(key1 = recordItem.materialId) {
                            bookInfo.value = bookViewModel.getBookInfoById(recordItem.materialId)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            onClick = {
                                recordViewModel.getRecordState(recordItem.recordId, userId)
                                bookViewModel.getBookState(recordItem.materialId)
                                navController.navigate(RecordPage.Detail.route)
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
                                        .padding(
                                            top = 13.dp,
                                            start = 11.dp,
                                            end = 16.dp,
                                            bottom = 20.dp
                                        ),
                                ) {
                                    Text(
                                        text = bookInfo.value?.title ?: "",
                                        style = MaterialTheme.typography.titleMedium,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.padding(bottom = 5.dp)
                                    )
                                    val author = bookInfo.value?.author ?: ""
                                    Text(
                                        text = "Author : $author",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "Summary",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    var summary = ""
                                    if(recordItem.summary.length < 30){
                                        summary = recordItem.summary
                                    }
                                    else{
                                        summary = recordItem.summary.take(30) + "..."
                                    }
                                    Text(
                                        text = summary,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Normal,
                                    )

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
