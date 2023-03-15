package com.honey.randomusergenerator.ui.screens.generator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState


@Composable
fun GeneratorScreen(
    state: State<GeneratorState>,
    effect: State<GeneratorEffect?>,
    onEventSent: (event: GeneratorEvent)-> Unit,
    onNavigationRequested: (navEffect: GeneratorEffect) -> Unit
) {
    when(val state = state.value){
        is GeneratorState.Generating -> {}
        is GeneratorState.ShowUsers -> {}
        is GeneratorState.Error -> {}
        is GeneratorState.Empty -> {}
    }

    when(val effect = effect.value){
        is GeneratorEffect.NavBack -> {}
        null -> {}
    }
}