package com.honey.randomusergenerator.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEvent
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsState

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    state: State<SettingsState>,
    onEventSend: (event: SettingsEvent) -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(id = R.string.settings_title),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            when(val state = state.value){
                is SettingsState.Show -> {
                    Column(
                        modifier = Modifier.fillMaxSize(0.95f)
                    ) {
                        ItemWithCheckbox(
                            text = stringResource(id = R.string.developer_mode),
                            defValue = state.developerMode,
                            onValueChanged = {newValue -> onEventSend.invoke(SettingsEvent.DeveloperMode(newValue))}
                        )
                        if (state.developerMode){
                            val expanded = remember{ mutableStateOf(false)}

                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded.value = !expanded.value }
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Box {
                                        Text(text = "Selected: ")
                                        Text(text = "Kotlin")
                                    }
                                    if (expanded.value){
                                        Icon(
                                            painter = rememberVectorPainter(Icons.Default.KeyboardArrowDown),
                                            contentDescription = "Down Arrow"
                                        )
                                    } else{
                                        Icon(
                                            painter = rememberVectorPainter(Icons.Default.KeyboardArrowLeft),
                                            contentDescription = "Left Arrow"
                                        )
                                    }
                                }
                            }

                            DropdownMenu(
                                modifier = Modifier.align(Alignment.End),
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false }
                            ) {
                                DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ })
                            }
                        }
                    }
                }
                is SettingsState.Loading -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {}
            }
        },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.ok),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}

@Composable
fun ItemWithCheckbox(
    modifier: Modifier = Modifier,
    text : String,
    defValue : Boolean,
    onValueChanged : (value : Boolean) -> Unit
){
    val checkBoxValue = remember { mutableStateOf(defValue)}
    Card(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = text)
            Checkbox(
                checked = checkBoxValue.value,
                onCheckedChange = { newValue ->
                    checkBoxValue.value = newValue
                    onValueChanged.invoke(newValue)
                }
            )
        }
    }
}