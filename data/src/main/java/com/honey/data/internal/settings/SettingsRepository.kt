package com.honey.data.internal.settings

interface SettingsRepository {
    fun exportLanguage(language: String? = null) : String
    fun developerMode(devMode: Boolean? = null) : Boolean?
}