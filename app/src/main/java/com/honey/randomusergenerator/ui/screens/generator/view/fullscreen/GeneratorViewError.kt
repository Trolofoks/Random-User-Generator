package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.data.model.Constance
import com.honey.randomusergenerator.data.model.Errors
import com.honey.randomusergenerator.ui.navigation.Screens
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import kotlinx.coroutines.delay

@Composable
fun GeneratorViewError(
    state: GeneratorState.Error
) {
    val retrying = remember { mutableStateOf(false  )}
    val refreshTrigger = remember { mutableStateOf(0)}
    RefreshTimer(refresh = retrying, trigger = refreshTrigger)

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Card(modifier = Modifier.fillMaxWidth(0.9f)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorTextChooser(state.error),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "Retry?", style = MaterialTheme.typography.bodySmall)
                Box(
                    modifier = Modifier.size(64.dp),
                    content = {
                    if (retrying.value){
                        CircularProgressIndicator(
                            modifier = Modifier.size(56.dp),
                        )
                    } else {
                        IconButton(
                            modifier = Modifier.size(64.dp),
                            onClick = {
                            refreshTrigger.value++
                        }) {
                            Icon(painter = rememberVectorPainter(image = Icons.Default.Update),
                                contentDescription = "refresh")
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun RefreshTimer(refresh: MutableState<Boolean>, trigger : MutableState<Int>){
    LaunchedEffect(key1 = trigger) {
        refresh.value = true
        delay(5000)
        refresh.value = false
    }
}

fun errorTextChooser(error: String) : String{
    return when (error){
        Errors.SERVER_UNAVAILABLE -> {
            "Server Problem"
        }
        Errors.NO_INTERNET_ACCESS -> {
            "No Internet Access"
        }
        else -> {
            "Error"
        }
    }
}

@Preview
@Composable
fun GVEPreview(){
    GeneratorViewError(
        state = GeneratorState.Error(error = Errors.SERVER_UNAVAILABLE)
    )
}