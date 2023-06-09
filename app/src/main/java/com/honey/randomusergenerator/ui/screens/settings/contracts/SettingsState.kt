package com.honey.randomusergenerator.ui.screens.settings.contracts

import com.honey.data.settings.model.CopyType
import com.honey.randomusergenerator.ui.base.ViewState

sealed class SettingsState : ViewState {
    object Loading : SettingsState()
    data class Show(val developerMode: Boolean, val selectedLanguage: CopyType) : SettingsState()
}
