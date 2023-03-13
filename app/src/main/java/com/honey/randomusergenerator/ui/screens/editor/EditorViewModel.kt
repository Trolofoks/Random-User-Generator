package com.honey.randomusergenerator.ui.screens.editor

import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState

class EditorViewModel(
    //TODO(repository)
):BaseViewModel<EditorEvent, EditorState, EditorEffect>(
    initialState = EditorState.ShowEditor
) {

    override fun obtainEvent(event: EditorEvent) {
        when(event){
            is EditorEvent.TryToSave -> {}
        }
    }

}