package com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

@Composable
fun FavoriteViewLoading(
    state: FavoriteState.Loading,
) {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Fetching your favorite users",
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f))

            }
        }
    }
}

@Preview
@Composable
fun PreviewFVL() {
    FavoriteViewLoading(state = FavoriteState.Loading)
}