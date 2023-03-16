package com.honey.randomusergenerator.ui.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.randomusergenerator.ui.screens.editor.EditorScreen
import com.honey.randomusergenerator.ui.screens.editor.EditorViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditorRoute(
    onSettingsClick: (String) -> Unit
){
    val viewModel = getViewModel<EditorViewModel>()
    EditorScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect().collectAsState(initial = null),
        onEventSent = {event -> viewModel.obtainEvent(event)},
        onSettingsClick = onSettingsClick
    )
}