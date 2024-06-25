package com.androidalliance.mynilam.ui.screen.mainfeatures.profile

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidalliance.mynilam.MainActivity
import com.androidalliance.mynilam.R
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.navigation.ProfilePage
import com.androidalliance.mynilam.navigation.ReviewPage
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel
import com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    // User State
    viewModel.showTopAppBar()
    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val profileState = profileViewModel.sharedProfileState.collectAsStateWithLifecycle()

    val username by remember {
        derivedStateOf {
            userState.value?.username ?: ""
        }
    }
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: 0
        }
    }
    profileViewModel.getProfileState(userId)
    val profile by remember {
        derivedStateOf {
            profileState.value
        }
    }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
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
                    text = "My Profile",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
                IconButton(
                    onClick = {
                        navController.navigate(ProfilePage.EditForm.route)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_bg),
                    contentDescription = "Profile Background Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .aspectRatio(16f / 9f)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(R.drawable.defaultpfp),
                            contentDescription = "NULL",
                            modifier = Modifier
                                .size(120.dp)
                                .padding(start = 16.dp)
                                .shadow(shape = CircleShape, elevation = 10.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {

                        //can add anything about user profile here
                        Text(
                            text = "@$username",
                            modifier = Modifier.padding(start = 5.dp),
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "${profile?.firstName} ${profile?.lastName}",
                            modifier = Modifier.padding(start = 5.dp),
                            fontWeight = FontWeight.Black,
                            fontSize = 33.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = "${profile?.motto}",
                            modifier = Modifier.padding(start = 5.dp),
                            fontWeight = FontWeight.Thin,
                            fontSize = 15.sp
                        )
                    }
                }


            }
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    onClick = {
                        navController.navigate(MainScreen.Root.route){
                            popUpTo(MainScreen.Main.route){
                                inclusive = true
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Output,
                            contentDescription = "Sign Out",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = "Sign Out")
                    }
                }
            }
        }

    }
}