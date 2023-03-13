package com.honey.randomusergenerator.ui.screens.editor.contract

import com.honey.randomusergenerator.ui.base.ViewState

sealed class EditorState: ViewState{
    object Saving : EditorEvent()
    object ShowEditor : EditorEvent()
    object Error: EditorEvent()
}

