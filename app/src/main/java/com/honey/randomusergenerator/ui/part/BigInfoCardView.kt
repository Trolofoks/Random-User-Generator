package com.honey.randomusergenerator.ui.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.honey.data.settings.model.CopyType
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.data.model.Holder
import kotlinx.coroutines.launch

@Composable
fun BigInfoCardView(
    modifier: Modifier = Modifier,
    user: User,
    favorite: ((user: User, add: Boolean) -> Unit?)? = null,
    inFavChecked: Boolean = false,
    exportCopyTypeFormat: CopyType = CopyType.EDITED,
    ) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val addInFavChecked = remember { mutableStateOf(inFavChecked) }

    val coroutineScope = rememberCoroutineScope()

    fun showSnackbar(text: String){
        coroutineScope.launch {
            Holder.snackbarHostState.showSnackbar(
                message = text,
                duration = SnackbarDuration.Short
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Box(modifier = modifier, contentAlignment = Alignment.Center) {

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    ) {
                        IconToggleButton(
                            modifier = Modifier.align(Alignment.TopEnd),
                            checked = addInFavChecked.value, onCheckedChange = {
                                favorite?.invoke(user, it)
                                addInFavChecked.value = it
                            }) {
                            if (addInFavChecked.value) {
                                showSnackbar(stringResource(id = R.string.added_to_favorite))
                                Icon(
                                    tint = MaterialTheme.colorScheme.secondary,
                                    painter = rememberVectorPainter(image = Icons.Default.Favorite),
                                    contentDescription = stringResource(id = R.string.remove_from_favorite)
                                )
                            } else {
                                Icon(
                                    tint = MaterialTheme.colorScheme.secondary,
                                    painter = rememberVectorPainter(image = Icons.Default.FavoriteBorder),
                                    contentDescription = stringResource(id = R.string.added_to_favorite)
                                )
                            }
                        }
                        val snackbarCopyText = stringResource(id = R.string.copied)
                        val snackbarCopyTextRaw = stringResource(id = R.string.raw_copied)
                        IconButton(onClick = {
                            when(exportCopyTypeFormat){
                                CopyType.EDITED -> {
                                    clipboardManager.setText(
                                        AnnotatedString(buildString {
                                            append("Name:\n${user.name}\n\n")
                                            append("Email:\n${user.email}\n\n")
                                            append("Birthday:\n${user.birthday}\n\n")
                                            append("Address:\n${user.address}\n\n")
                                            append("Number:\n${user.number}\n\n")
                                            append("Password:\n${user.password}\n\n")
                                            append("Image:\n${user.avatarURL}")
                                        })
                                    )
                                    showSnackbar(snackbarCopyText)
                                }
                                CopyType.RAW -> {
                                    clipboardManager.setText(AnnotatedString(user.toString()))
                                    showSnackbar(snackbarCopyTextRaw)
                                }
                                else -> {}
                            }
                        }, modifier = Modifier.align(Alignment.TopStart)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_copy),
                                contentDescription = "Copy Card",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                        CircularProgressIndicator(Modifier.size(98.dp))
                        Image(
                            painter = rememberAsyncImagePainter(user.picture ?: user.avatarURL),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillBounds
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


}

@Composable
fun InfoItem(paramName: String, paramValue: String) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)

    ) {
        Text(
            text = "$paramName ", modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .weight(0.3f)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(4.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Box(modifier = Modifier.size(4.dp))
        Text(
            text = paramValue, modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    clipboardManager.setText(AnnotatedString(paramValue))
                }
                .weight(0.7f)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(4.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}