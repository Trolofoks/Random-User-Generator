package com.honey.randomusergenerator.ui.screens.generator.view.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.DrawerDefaults.containerColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.honey.randomusergenerator.data.model.User
import java.util.jar.Attributes.Name

@Composable
fun BigInfoCardView(user: User) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth(0.9f)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            ) {
                Box (contentAlignment = Alignment.Center, modifier = Modifier.align(Alignment.CenterHorizontally)){
                    CircularProgressIndicator(Modifier.size(98.dp))
                    Image(
                        painter = rememberAsyncImagePainter(user.avatarURL),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                }

                InfoItem(paramName = "Name", paramValue = user.name)
                InfoItem(paramName = "Email", paramValue = user.email)
                InfoItem(paramName = "Birthday", paramValue = user.birthday)
                InfoItem(paramName = "Address", paramValue = user.address)
                InfoItem(paramName = "Number", paramValue = user.number)
                InfoItem(paramName = "Password", paramValue = user.password)


            }
        }
    }
}

@Composable
private fun InfoItem(paramName: String, paramValue: String){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)) {
        Text(text = "$paramName ", modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .weight(0.3f)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(4.dp),
            color = MaterialTheme.colorScheme.onSecondary)
        Box(modifier = Modifier.size(4.dp))
        Text(text = paramValue, modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .weight(0.7f)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(4.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Composable
fun GICVPreview() {
    BigInfoCardView(
        user = User(
            avatarURL = "https://randomuser.me/api/portraits/med/men/75.jpg",
            name = "Misty Cooper",
            email = "mistycooper@example.com",
            birthday = "2/4/1949",
            address = "8367 Cackson St",
            number = "(965) 464-1400",
            password = "ernie",
        )
    )
}