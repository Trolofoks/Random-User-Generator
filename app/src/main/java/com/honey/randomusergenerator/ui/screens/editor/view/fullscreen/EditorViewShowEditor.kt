package com.honey.randomusergenerator.ui.screens.editor.view.fullscreen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.honey.data.model.GenerateKeys
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorViewShowEditor(
    state: EditorState.ShowEditor,
    onUserComplete : (user: User) -> Unit,
    getByKey: (key: String) -> Unit
) {
    val contentResolver = LocalContext.current.contentResolver


    val showImageSelector = remember { mutableStateOf(false) }

    val selectedUri = remember { mutableStateOf<Uri?>(null) }
    val selectedImageByteArray = remember { mutableStateOf<ByteArray?>(null) }

    val launchPickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri->
            selectedUri.value = uri
            Log.d("MyLog", "uri $uri")
        }
    )

    val name = remember { mutableStateOf("")}
    val email = remember { mutableStateOf("")}
    val birthday = remember { mutableStateOf("")}
    val address = remember { mutableStateOf("")}
    val number = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}

    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Card(modifier = Modifier.fillMaxWidth(0.95f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable(onClick = {showImageSelector.value = !showImageSelector.value})){
                        CircularProgressIndicator(modifier = Modifier.size(60.dp))
                        Image(painter = rememberAsyncImagePainter(model = if(selectedUri.value != null){selectedUri.value} else {state.avatarUrl}),
                            contentDescription = "Avatar", modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape), contentScale = ContentScale.FillBounds)
                    }
                    if (showImageSelector.value){
                        Row {
                            Spacer(modifier = Modifier.weight(0.06f))
                            Button(onClick = {
                                getByKey.invoke(GenerateKeys.AVATAR)
                                selectedUri.value = null
                            }, Modifier.weight(0.41f)) {
                                Text(text = "Generate")
                            }
                            Spacer(modifier = Modifier.weight(0.06f))
                            Button(onClick = {
                                launchPickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }, Modifier.weight(0.41f)) {
                                Text(text = "SelectMy")
                            }
                            Spacer(modifier = Modifier.weight(0.06f))
                        }
                    }
                    CompletedTextFieldByKey(key = GenerateKeys.NAME, userField = name, stateField = state.name, onGenerate = {getByKey(it)})
                    CompletedTextFieldByKey(key = GenerateKeys.EMAIL, userField = email, stateField = state.email, onGenerate = {getByKey(it)})
                    CompletedTextFieldByKey(key = GenerateKeys.BIRTHDAY, userField = birthday, stateField = state.birthday, onGenerate = {getByKey(it)})
                    CompletedTextFieldByKey(key = GenerateKeys.ADDRESS, userField = address, stateField = state.address, onGenerate = {getByKey(it)})
                    CompletedTextFieldByKey(key = GenerateKeys.NUMBER, userField = number, stateField = state.number, onGenerate = {getByKey(it)})
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        value = password.value,
                        onValueChange = {newValue -> password.value = newValue},
                        singleLine = true, label = { Text(text = "Password")}
                    )
                    Button(onClick = {
                        onUserComplete.invoke(
                            User(
                                avatarURL = state.avatarUrl,
                                name = name.value.ifEmpty { state.name },
                                email = email.value.ifEmpty { state.email },
                                birthday = birthday.value.ifEmpty { state.birthday },
                                address = address.value.ifEmpty { state.address },
                                number = number.value.ifEmpty { state.number },
                                password = password.value,
                                picture = if (selectedUri.value != null){
                                    getByteArray(inputStream = contentResolver.openInputStream(selectedUri.value!!))
                                } else {
                                    null
                                }
                            )
                        )
                    }) {
                        Text(text = "Save!")
                    }
                }
            }
        }
    }
}

fun getByteArray(inputStream: InputStream?): ByteArray{
    try {
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, stream)
        val byteArray = stream.toByteArray()

        return byteArray
    } catch (e: IOException){
        throw e
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedTextFieldByKey(key: String, userField:  MutableState<String>, stateField: String, onGenerate: (Key : String)-> Unit){
    val userShow = remember { mutableStateOf(false )}
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = if (userShow.value){userField.value} else {stateField},
        onValueChange = {newValue ->
            userField.value = newValue
            userShow.value = true
        },
        singleLine = true, label = { Text(text = key)},
        trailingIcon = { IconButton(onClick = {
            onGenerate.invoke(key)
            userShow.value = false
        }) {
            Icon(painter = rememberVectorPainter(
                image = Icons.Default.Refresh),
                contentDescription = "Get random")
        }},

        )
}