package com.honey.randomusergenerator.ui.screens.editor

import android.content.SharedPreferences.Editor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState
import com.honey.randomusergenerator.ui.screens.editor.view.fullscreen.EditorViewLoading
import com.honey.randomusergenerator.ui.screens.editor.view.fullscreen.EditorViewSaving
import com.honey.randomusergenerator.ui.screens.editor.view.fullscreen.EditorViewShowEditor

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
            EditorViewShowEditor(
                state = state,
                onUserComplete = {user ->
                    onEventSent(EditorEvent.TryToSave(user = user))
                },
                getByKey = { generateKey ->
                    onEventSent(EditorEvent.GenerateByKey(generateKey))
                }
            )
        }
        is EditorState.Saving -> {
            EditorViewSaving()
        }
        is EditorState.Loading -> {
            EditorViewLoading()
        }
    }
}