package com.androidalliance.mynilam.ui.screen.mainfeatures.review

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.data.models.Review
import com.androidalliance.mynilam.data.models.User
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.navigation.ReviewPage
import com.androidalliance.mynilam.ui.components.RatingStar
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel.BookViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel.ReviewViewModel

@Composable
fun ReviewDetailScreen(
    navController: NavHostController,
    reviewViewModel: ReviewViewModel,
    bookViewModel: BookViewModel,
    viewModel: UserViewModel
) {
    // Urus State dulu
    viewModel.hideTopAppBar()
    val bookState = bookViewModel.sharedBookState.collectAsStateWithLifecycle()
    val reviewState = reviewViewModel.sharedReviewInfo.collectAsStateWithLifecycle()
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: 0
        }
    }

    val bookItem by remember{
        derivedStateOf {
            bookState.value ?: ReadingMaterial(
                title = "Title",
                author = "Author",
                type = "Undefined Type",
                genre = "Undefined Genre",
                publication = "Undefined Publication",
                publicationYear = 2000
            )

        }
    }
    val reviewItem by remember{
        derivedStateOf {
            reviewState.value ?: Review(
                comment = "Summary",
                star = 0,
                userId = 0,
                materialId = 0
            )
        }
    }
    val thisUser = remember {
        mutableStateOf<User?>(null)
    }
    LaunchedEffect(key1 = reviewItem.userId) {
        thisUser.value = viewModel.getUserInfoById(reviewItem.userId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column {
                // Letak BG
            }
        }
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .shadow(
                        elevation = 15.dp,
                        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
                    ),
                shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.82f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        //
                        Text(
                            text = bookItem.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            lineHeight = 16.sp
                        )
                        //
                        Spacer(modifier = Modifier.height(10.dp))
                        //
                        RatingStar(
                            rating = reviewItem.star,
                        )
                        //

                        if (reviewItem.userId == userId){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    modifier = Modifier
                                        .padding(vertical = 20.dp),
                                    onClick = {
                                        navController.navigate(ReviewPage.EditForm.route)
                                    }
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))
                                Button(
                                    modifier = Modifier
                                        .padding(vertical = 20.dp),
                                    onClick = {
                                        reviewViewModel.deleteReview(reviewItem)
                                        navController.navigate(MainScreen.Review.route) {
                                            popUpTo(ReviewPage.Detail.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Edit",
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                        //
                        Spacer(modifier = Modifier.height(10.dp))
                        //
                        Text(
                            text = "Review by : ${thisUser.value?.username ?: ""}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            lineHeight = 16.sp
                        )
                        //
                        Spacer(modifier = Modifier.height(10.dp))
                        //
                        Text(
                            text = reviewItem.comment,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .fillMaxHeight(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Book")
                    }
                }
            }
        }
    }
}