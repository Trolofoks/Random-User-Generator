package com.honey.randomusergenerator.ui.screens.generator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import com.honey.randomusergenerator.ui.screens.generator.view.fullscreen.GeneratorViewEmpty
import com.honey.randomusergenerator.ui.screens.generator.view.fullscreen.GeneratorViewError
import com.honey.randomusergenerator.ui.screens.generator.view.fullscreen.GeneratorViewGenerating
import com.honey.randomusergenerator.ui.screens.generator.view.fullscreen.GeneratorViewShowUsers


@Composable
fun GeneratorScreen(
    state: State<GeneratorState>,
    effect: State<GeneratorEffect?>,
    onEventSent: (event: GeneratorEvent)-> Unit,
    onSettingsClick: (String) -> Unit
) {
    when(val state = state.value){
        is GeneratorState.Generating -> {
            GeneratorViewGenerating()
        }
        is GeneratorState.ShowUsers -> {
            //GeneratorViewShowUsers()
        }
        is GeneratorState.Error -> {
            //GeneratorViewError()
        }
        is GeneratorState.Empty -> {
            GeneratorViewEmpty(
                onGenerate = {
                    onEventSent.invoke(GeneratorEvent.Generate)
                }
            )
        }
    }

    when(val effect = effect.value){
        is GeneratorEffect.NavBack -> {}
        null -> {}
    }
}