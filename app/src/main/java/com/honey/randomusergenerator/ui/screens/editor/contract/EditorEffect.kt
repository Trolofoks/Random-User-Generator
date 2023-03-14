package com.honey.randomusergenerator.ui.screens.editor.contract

import com.honey.randomusergenerator.ui.base.ViewEffect

sealed class EditorEffect: ViewEffect {
    object NavBack : EditorEffect()
}