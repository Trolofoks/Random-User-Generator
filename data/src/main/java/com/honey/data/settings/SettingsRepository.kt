package com.honey.data.settings

import com.honey.data.settings.model.CopyType

interface SettingsRepository {
    fun exportLanguage(copyType: CopyType? = null) : CopyType
    fun developerMode(devMode: Boolean? = null) : Boolean?
}