package com.honey.randomusergenerator.ui.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.randomusergenerator.ui.screens.settings.SettingsDialog
import com.honey.randomusergenerator.ui.screens.settings.SettingsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsDialogRoute(
    onDismiss: () -> Unit,
) {
    val viewModel = getViewModel<SettingsViewModel>()
    SettingsDialog(
        onDismiss = onDismiss,
        state = viewModel.getViewState().collectAsState(),
        onEventSend = {event -> viewModel.obtainEvent(event)}
    )
}