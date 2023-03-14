package com.honey.randomusergenerator.ui.screens.editor

import androidx.compose.runtime.Composable
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState

@Composable
fun EditorScreen(
    state: EditorState,
    effectFlow: EditorEffect?,
    onEventSent: (event: EditorEvent)-> Unit,
    onNavigationRequested: (navEffect: EditorEffect) -> Unit
){
    when(state){
        is EditorState.ShowEditor -> {}
        is EditorState.Error -> {}
        is EditorState.Saving -> {}
    }

}