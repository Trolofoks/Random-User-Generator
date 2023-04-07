package com.honey.randomusergenerator.ui.screens.settings.contracts

import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class SettingsEvent : ViewEvent{
    data class DeveloperMode (val turnOn : Boolean) : SettingsEvent()
    data class LanguageSelect(val language: Language) : SettingsEvent()
}

sealed class Language {
    object Kotlin: Language()
    object AnotherLanguage: Language()
}
