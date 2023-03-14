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
        when(viewState){
            is EditorState.ShowEditor -> {reduce(event, viewState as EditorState.ShowEditor)}
            is EditorState.Error -> {reduce(event, viewState as EditorState.Error)}
            is EditorState.Saving -> {reduce(event, viewState as EditorState.Saving)}
        }
    }

    private fun reduce(event: EditorEvent, currentState: EditorState.ShowEditor){

    }
    private fun reduce(event: EditorEvent, currentState: EditorState.Error){

    }
    private fun reduce(event: EditorEvent, currentState: EditorState.Saving){

    }

}