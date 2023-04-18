package com.honey.randomusergenerator.ui.screens.settings.contracts

import com.honey.data.settings.model.CopyType
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class SettingsEvent : ViewEvent{
    data class DeveloperMode (val turnOn : Boolean) : SettingsEvent()
    data class CopyMode(val copy: CopyType) : SettingsEvent()
}


