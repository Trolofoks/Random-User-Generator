package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GeneratorViewGenerating(
    state: GeneratorState.Generating
) {
    val facts =  remember { mutableStateOf("") }
    LaunchedEffect(key1 = "Key"){
        interestingGenerator(facts)
    }

    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Do NOT close the application", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 32.dp))
                Text(text = "Interesting Facts:", style = MaterialTheme.typography.bodySmall)
                Text(text = facts.value, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 32.dp))
//                CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f))
                //TODO(WHAT A SHIT)
                Box(){
                    Box(contentAlignment = Alignment.BottomEnd){
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.45f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.4f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.35f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.3f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.25f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.2f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.15f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.1f))
                    }
                    Box(){
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.45f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.4f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.35f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.3f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.25f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.2f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.15f))
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.1f))
                    }
                }

            }
        }
    }
}


suspend fun interestingGenerator(fact: MutableState<String>){
    val hardCodeFacts = listOf<String>(
        "Now you see interesting fact.",
        "Maybe in future here you can see something interesting.",
        "This is useless text.",
        "Why are you gay?",
        "You have a nice meal today?",
        "This application created by just me one.",
        "You can send some text to my mail, and maybe i will add it here.",
        "When I type this I want to sleep, but don't worry, just a little.",
        "If you read this, you cool boy/girl ha-ha."
    )
    fact.value = hardCodeFacts.random()
    for (i in 0..100){
        delay(6500)
        fact.value = hardCodeFacts.random()
    }}