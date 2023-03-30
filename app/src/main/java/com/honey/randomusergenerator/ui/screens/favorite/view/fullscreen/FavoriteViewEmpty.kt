package com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

@Composable
fun FavoriteViewEmpty(
    state: FavoriteState.Empty,
    retryLoad: (() -> Unit)? = null 
) {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.9f)) {
                Text(
                    text = "Hi, you don't have any saved users, try to generate some, and it add to favorite",
                    modifier = Modifier.padding(bottom = 32.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Text(text = "Yes, you need press button like this ->  ")
                    Icon(painter = rememberVectorPainter(image = Icons.Default.FavoriteBorder),
                        contentDescription = "Example")
                }
            }

        }
    }
}

@Preview
@Composable
fun FavoriteViewEmptyPreview(){
    FavoriteViewEmpty(state = FavoriteState.Empty)
}