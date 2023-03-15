package com.honey.randomusergenerator.ui.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.honey.randomusergenerator.ui.screens.editor.EditorScreen
import com.honey.randomusergenerator.ui.screens.editor.EditorViewModel
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import org.koin.androidx.compose.getViewModel

@Composable
fun EditorScreenDestination(navController: NavController){
    val viewModel = getViewModel<EditorViewModel>()
    EditorScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect().collectAsState(initial = null),
        onEventSent = {event -> viewModel.obtainEvent(event)},
        onNavigationRequested = {navEffect->
            when (navEffect){
                is EditorEffect.NavBack -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    )
}