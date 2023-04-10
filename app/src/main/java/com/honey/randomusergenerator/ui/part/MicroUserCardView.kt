package com.honey.randomusergenerator.ui.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.honey.randomusergenerator.data.model.User

@Composable
fun MicroUserCardView(
    modifier : Modifier = Modifier,
    user: User,
    onCardClick: (user: User) -> Unit
) {
    Box(modifier = modifier.aspectRatio(1f).clickable {
        onCardClick.invoke(user)
    }, contentAlignment = Alignment.Center){
        Card(modifier = Modifier
            .fillMaxSize(0.95f)
            .padding(2.dp)) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator(modifier = Modifier.padding(3.dp))
                Image(painter = rememberAsyncImagePainter(model = user.avatarURL),
                    contentDescription = user.name,
                    modifier = Modifier.clip(CircleShape).fillMaxSize().padding(2.dp)
                )
            }
        }
    }
}