package com.honey.randomusergenerator.ui.screens.editor.contract

import com.honey.randomusergenerator.ui.base.ViewState

sealed class EditorState: ViewState{
    object Saving : EditorState()
    object Loading : EditorState()
    data class ShowEditor(
        val avatarUrl: String = "https://randomuser.me/api/portraits/med/men/75.jpg",
        val name: String = "",
        val email: String = "",
        val birthday: String = "",
        val address: String = "",
        val number: String = ""
    ) : EditorState()
    object Error: EditorState()
}

