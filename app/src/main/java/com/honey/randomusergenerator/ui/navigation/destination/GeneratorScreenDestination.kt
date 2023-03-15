package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.generator.GeneratorScreen
import com.honey.randomusergenerator.ui.screens.generator.GeneratorViewModel
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import org.koin.androidx.compose.getViewModel

@Composable
fun GeneratorScreenDestination(navController: NavController){
    val viewModel = getViewModel<GeneratorViewModel>()

    GeneratorScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect().collectAsState(initial = null),
        onEventSent = {event -> viewModel.obtainEvent(event)},
        onNavigationRequested = {navEffect->
            when (navEffect){
                is GeneratorEffect.NavBack -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    )
}