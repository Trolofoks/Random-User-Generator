package com.honey.randomusergenerator.ui.screens.editor

import androidx.compose.runtime.Composable
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState

@Composable
fun EditorScreen(
    state: EditorState
){
    when(state){
        is EditorState.ShowEditor -> {}
        is EditorState.Error -> {}
        is EditorState.Saving -> {}
    }

}