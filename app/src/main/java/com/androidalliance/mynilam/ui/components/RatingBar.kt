package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingStar(
    rating: Int = 0,
    starRating: Int = 5,
    starColor: Color = MaterialTheme.colorScheme.primary,
    onRatingChanged: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    ) {
    Row {
        for(index in 1 .. starRating){
            Icon(
                modifier = modifier.clickable { onRatingChanged(index) },
                imageVector = if(index <= rating) Icons.Rounded.Star else Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starColor
            )
        }
    }
}