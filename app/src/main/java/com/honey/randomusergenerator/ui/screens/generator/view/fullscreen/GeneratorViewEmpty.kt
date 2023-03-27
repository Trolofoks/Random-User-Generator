package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import com.honey.randomusergenerator.ui.screens.generator.view.part.BigInfoCardView
import com.honey.randomusergenerator.ui.screens.generator.view.part.GICVPreview

@Composable
fun GeneratorViewEmpty(
    state: GeneratorState.Empty,
    modifier: Modifier = Modifier,
    onGenerate: (amount: Int) -> Unit
) {
    val counter = remember{ mutableStateOf(1)}
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Center
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Center),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Want to Generate Users?")
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            onClick = {
                                if (counter.value > 1){
                                    counter.value--
                                }
                            }) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minus")
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            text = "${counter.value}"
                        )
                        IconButton(
                            onClick = {
                                if (counter.value < 10){
                                    counter.value++
                                }
                            }) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Plus")
                        }
                    }
                    Button(
                        onClick = { onGenerate.invoke(counter.value) }
                    ) {
                        Text(text = "Generate!")
                    }
                }
            }
        }
    }
}