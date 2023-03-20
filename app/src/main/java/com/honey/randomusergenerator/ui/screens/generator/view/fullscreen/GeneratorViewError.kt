package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
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

    LaunchedEffect(refreshTrigger.value) {
        //TODO(add real refresh)
        retrying.value = true
        delay(5000)
        retrying.value = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Card(modifier = Modifier.fillMaxWidth(0.9f)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorTextChooser(state.error),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "Retry?", style = MaterialTheme.typography.bodySmall)
                Box(
                    modifier = Modifier.height(64.dp).padding(top = 8.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                    if (retrying.value){
                        CircularProgressIndicator(
                            modifier = Modifier.size(56.dp),
                        )
                    } else {
                        Button(onClick = { refreshTrigger.value++ }) {
                            Text(text = "Refresh!")
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