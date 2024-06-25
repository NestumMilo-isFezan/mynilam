package com.androidalliance.mynilam.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingStar(
    rating: Int = 0,
    starRating: Int = 5,
    starColor: Color = MaterialTheme.colorScheme.primary,
    onRatingChanged: (Int) -> Unit = {},
    clickable: Boolean = true,
    modifier: Modifier = Modifier,
    ) {
    Row (
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(clickable){
            for(index in 1 .. starRating){
                Icon(
                    modifier = modifier.selectable(
                        selected = index <= rating,
                        onClick = { onRatingChanged(index) }
                    ),
                    imageVector = if(index <= rating) Icons.Rounded.Star else Icons.Outlined.StarOutline,
                    contentDescription = null,
                    tint = starColor
                )
            }
        }else {
            for (index in 1..starRating) {
                Icon(
                    modifier = modifier,
                    imageVector = if (index <= rating) Icons.Rounded.Star else Icons.Outlined.StarOutline,
                    contentDescription = null,
                )
            }
        }
    }
}