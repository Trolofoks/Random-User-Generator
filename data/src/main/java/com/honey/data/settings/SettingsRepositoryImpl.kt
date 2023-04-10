package com.honey.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.honey.data.settings.model.Language
import com.honey.data.settings.model.SettingsKeys

class SettingsRepositoryImpl(context: Context): SettingsRepository {
    private val sharedPreferences : SharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE)}

    override fun exportLanguage(language: String?): String {
        if (language != null){
            sharedPreferences.edit().putString(SettingsKeys.LANGUAGE, language).apply()
            return ""
        } else {
            val result = sharedPreferences.getString(SettingsKeys.LANGUAGE, Language.BASE)?: Language.BASE
            return result
        }
    }

    override fun developerMode(devMode: Boolean?): Boolean? {
        if (devMode != null){
            sharedPreferences.edit().putBoolean(SettingsKeys.DEV_MODE, devMode).apply()
            return null
        } else {
            val result = sharedPreferences.getBoolean(SettingsKeys.LANGUAGE, false)
            return result
        }
    }

}