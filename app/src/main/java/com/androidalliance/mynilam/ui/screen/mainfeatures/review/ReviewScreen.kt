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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.data.models.User
import com.androidalliance.mynilam.navigation.ReviewPage
import com.androidalliance.mynilam.ui.components.EmptyIconBg
import com.androidalliance.mynilam.ui.components.RatingStar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel.ReviewViewModel

@Composable
fun ReviewScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    reviewViewModel: ReviewViewModel,
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
) {
    // User State
    viewModel.showTopAppBar()
    reviewViewModel.findThisUserReview()
    val summaryReview = reviewViewModel.sharedReviewList.collectAsStateWithLifecycle()

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
                    text = "Book Review",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
                IconButton(
                    onClick = {
                        reviewViewModel.clearForm()
                        navController.navigate(ReviewPage.AddForm.route)
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
            Spacer(modifier = Modifier.height(30.dp))

            // LazyColumn For Listing
            if(summaryReview.value.isEmpty()){
                EmptyIconBg()
            }
            else {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(summaryReview.value) { reviewItem ->

                        val bookInfo = remember {
                            mutableStateOf<ReadingMaterial?>(null)
                        }
                        LaunchedEffect(key1 = reviewItem.materialId) {
                            bookInfo.value = bookViewModel.getBookInfoById(reviewItem.materialId)
                        }
                        val thisUser = remember {
                            mutableStateOf<User?>(null)
                        }
                        LaunchedEffect(key1 = reviewItem.userId) {
                            thisUser.value = viewModel.getUserInfoById(reviewItem.userId)
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            onClick = {
                                reviewViewModel.getReviewState(reviewItem.reviewId, reviewItem.userId)
                                bookViewModel.getBookState(reviewItem.materialId)
                                navController.navigate(ReviewPage.Detail.route)
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
                                ) {
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
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 2.dp, bottom = 5.dp)
                                    )

                                    ///
                                    RatingStar(rating = reviewItem.star, clickable = false)

                                    Text(
                                        text = "Reviewer : ${thisUser.value?.username ?: ""}",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(start = 2.dp, bottom = 5.dp),
                                    )

                                    var comments = ""
                                    if (reviewItem.comment.length < 15) {
                                        comments = reviewItem.comment
                                    } else {
                                        comments = reviewItem.comment.take(15) + "..."
                                    }
                                    Text(
                                        text = "Comments :",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 2.dp, bottom = 5.dp),
                                    )
                                    Text(
                                        text = comments,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(start = 2.dp, bottom = 5.dp),
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
