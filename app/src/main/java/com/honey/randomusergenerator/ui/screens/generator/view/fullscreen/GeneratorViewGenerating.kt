package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import kotlinx.coroutines.delay

@Composable
fun GeneratorViewGenerating(
    state: GeneratorState.Generating
) {
    val listOfInteresting = listOf(
        stringResource(id = R.string.interesting_1),
        stringResource(id = R.string.interesting_2),
        stringResource(id = R.string.interesting_3),
        stringResource(id = R.string.interesting_4),
        stringResource(id = R.string.interesting_5),
        stringResource(id = R.string.interesting_6),
        stringResource(id = R.string.interesting_7),
        stringResource(id = R.string.interesting_8),
        stringResource(id = R.string.interesting_9),
    )
    val factState =  remember { mutableStateOf("") }
    LaunchedEffect(key1 = "Key"){
        interestingGenerator(factState, listOfInteresting)
    }

    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.do_not_close), style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 32.dp))
                Text(text = stringResource(id = R.string.interesting_facts), style = MaterialTheme.typography.bodySmall)
                Text(text = factState.value, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 32.dp))
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f))

            }
        }
    }
}


suspend fun interestingGenerator(factState: MutableState<String>, facts : List<String>){
    factState.value = facts.random()
    for (i in 0..100){
        delay(7500)
        factState.value = facts.random()
    }
}