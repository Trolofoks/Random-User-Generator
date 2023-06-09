package com.honey.randomusergenerator.ui.part

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.honey.randomusergenerator.data.model.User

@Composable
fun SmallUserCardView(
    user: User,
    onCardClick: (user: User) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .padding(vertical = 4.dp),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onCardClick.invoke(user) }
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(54.dp))
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        painter = rememberAsyncImagePainter(user.picture ?: user.avatarURL),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.FillBounds
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(0.75f)
                        .size(64.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        InfoItem(paramName = "Name", paramValue = user.name)
                        InfoItem(paramName = "Number", paramValue = user.number)
                    }
//                    Box(
//                        modifier = Modifier.fillMaxWidth(),
//                        contentAlignment = Alignment.BottomEnd
//                    ) {
//                        Text(
//                            text = "Click for Full Info",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }
                }
            }
        }
    }
}

