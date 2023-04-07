package com.honey.randomusergenerator.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEvent
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsState
import org.koin.androidx.compose.getViewModel


@Composable
fun SettingsScreenShow(
    onDismiss: () -> Unit,
) {
    val viewModel = getViewModel<SettingsViewModel>()
    SettingsDialog(
        onDismiss = onDismiss,
        state = viewModel.getViewState().collectAsState(),
        onEventSend = {event -> viewModel.obtainEvent(event)}
    )
}

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    state: State<SettingsState>,
    onEventSend: (event: SettingsEvent) -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        modifier = Modifier.fillMaxSize(0.7f),
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
                            text = "Developer mode",
                            defValue = state.developerMode,
                            onValueChanged = {newValue -> onEventSend.invoke(SettingsEvent.DeveloperMode(newValue))}
                        )
                        if (state.developerMode){
                            val expanded = remember{ mutableStateOf(false)}

                            Card(modifier = Modifier.fillMaxWidth().clickable { expanded.value = !expanded.value }) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Box {
                                        Text(text = "Selected: ")
                                        Text(text = "Kotlin")
                                    }
                                    Icon(
                                        painter = rememberVectorPainter(Icons.Default.KeyboardArrowDown),
                                        contentDescription = "Down Arrow"
                                    )
                                }
                            }

                            DropdownMenu(expanded = expanded.value, onDismissRequest = { /*TODO*/ }) {
                                DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ })
                            }
                        }
                    }
                }
                is SettingsState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                else -> {}
            }
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