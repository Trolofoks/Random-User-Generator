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
import androidx.compose.ui.window.DialogProperties
import com.honey.data.settings.model.CopyType
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
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.fillMaxWidth(0.8f),
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
                    val expanded = remember{ mutableStateOf(false)}
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.copy_type), style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 12.dp))
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clickable { expanded.value = !expanded.value }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp, horizontal = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Row {
                                    Text(text = stringResource(id = R.string.selected))
                                    Text(text = state.selectedLanguage.type)
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
                            modifier = Modifier.fillMaxWidth(0.68f),
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                        ) {
                            DropdownMenuItem(text = { Text(text = stringResource(id = R.string.edited)) }, onClick = {
                                expanded.value = false
                                onEventSend.invoke(SettingsEvent.CopyMode(copy = CopyType.EDITED))
                            })
                            DropdownMenuItem(text = { Text(text = stringResource(id = R.string.raw)) }, onClick = {
                                expanded.value = false
                                onEventSend.invoke(SettingsEvent.CopyMode(copy = CopyType.RAW))
                            })
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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