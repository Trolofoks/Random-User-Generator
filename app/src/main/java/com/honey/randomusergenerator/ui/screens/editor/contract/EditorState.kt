package com.honey.randomusergenerator.ui.screens.editor.contract

import com.honey.randomusergenerator.ui.base.ViewState

sealed class EditorState: ViewState{
    object Saving : EditorState()
    object ShowEditor : EditorState()
    object Error: EditorState()
}

