package com.honey.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.honey.data.settings.model.CopyType
import com.honey.data.settings.model.SettingsKeys

class SettingsRepositoryImpl(context: Context): SettingsRepository {
    private val sharedPreferences : SharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE)}

    override fun exportLanguage(copyType: CopyType?): CopyType {
        if (copyType != null){
            sharedPreferences.edit().putString(SettingsKeys.Copy, copyType.type).apply()
            return CopyType.NO_RETURN
        }else {
            val result = sharedPreferences.getString(SettingsKeys.Copy, CopyType.EDITED.type)?: CopyType.EDITED.type
            return when (result){
                CopyType.RAW.type ->{CopyType.RAW}
                else ->{CopyType.EDITED}
            }

        }
    }

    override fun developerMode(devMode: Boolean?): Boolean? {
        if (devMode != null){
            sharedPreferences.edit().putBoolean(SettingsKeys.DEV_MODE, devMode).apply()
            return null
        } else {
            val result = sharedPreferences.getBoolean(SettingsKeys.Copy, false)
            return result
        }
    }

}