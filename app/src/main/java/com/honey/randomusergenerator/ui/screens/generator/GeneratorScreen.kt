package com.honey.randomusergenerator.ui.screens.generator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
            GeneratorViewGenerating(
                state = state
            )
        }
        is GeneratorState.ShowUsers -> {
            GeneratorViewShowUsers(
                state = state,
                onFavAdd = { user, add ->
                    onEventSent.invoke(GeneratorEvent.Favorite(user,add))
                },
                onFullInfoClick = {fullUser->
                    onEventSent.invoke(GeneratorEvent.FullInfoClick(fullUser))
                },
                onHideFullInfo = {
                    onEventSent.invoke(GeneratorEvent.HideFullInfo)
                },
                regenerate = {amount ->
                    onEventSent.invoke(GeneratorEvent.Regenerate(amount))
                }
            )
        }
        is GeneratorState.Error -> {
            GeneratorViewError(
                state = state,
                onRefresh = {
                    onEventSent.invoke(GeneratorEvent.Refresh)
                }
            )
        }
        is GeneratorState.Empty -> {
            GeneratorViewEmpty(
                state = state,
                onGenerate = {amount->
                    onEventSent.invoke(GeneratorEvent.Generate(amount))
                },
            )
        }
    }

    LaunchedEffect(key1 = effect.value){
        effect.value?.let {effect ->
            when(effect){
                else -> {}
            }
        }
    }
}