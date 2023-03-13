package com.honey.randomusergenerator.ui.screens.editor.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class EditorEvent: ViewEvent {
    data class TryToSave(
        val user: User
    ): EditorEvent()
}