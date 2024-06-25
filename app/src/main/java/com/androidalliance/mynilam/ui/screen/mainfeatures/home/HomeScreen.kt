@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidalliance.mynilam.ui.screen.mainfeatures.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.androidalliance.mynilam.R
import com.androidalliance.mynilam.navigation.MainScreen
import com.androidalliance.mynilam.ui.screen.auths.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.showTopAppBar()

    val userState = viewModel.sharedUserState.collectAsStateWithLifecycle()
    val username by remember {
        derivedStateOf {
            userState.value?.username ?: ""
        }
    }
    val userId by remember {
        derivedStateOf {
            userState.value?.uid ?: ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 270.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "Hello $username")
            Image(
                painter = painterResource(R.drawable.box_background),
                contentDescription = "Box Background Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(360.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .aspectRatio(4f / 3f)

            )
            Text(
                text = "Hello, ",
                modifier = Modifier.padding(start = 15.dp, bottom = 215.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20f.sp
            )
            Text(
                text = username,
                modifier = Modifier.padding(start = 20.dp, bottom = 160.dp),
                fontWeight = FontWeight.Black,
                fontSize = 40f.sp
            )
        }
    }
}