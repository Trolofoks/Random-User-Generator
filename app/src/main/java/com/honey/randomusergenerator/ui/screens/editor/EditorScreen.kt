package com.honey.randomusergenerator.ui.screens.editor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    state: State<EditorState>,
    effect: State<EditorEffect?>,
    onEventSent: (event: EditorEvent)-> Unit,
    onSettingsClick: (String) -> Unit
){
    when(val state = state.value){
        is EditorState.ShowEditor -> {

        }
        is EditorState.Error -> {

        }
        is EditorState.Saving -> {

        }
    }

    when(val effect = effect.value){
        is EditorEffect.NavBack -> {}
        null -> {}
    }

}