package com.honey.randomusergenerator.ui.screens.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.honey.data.internal.settings.SettingsRepository
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEffect
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEvent
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsState
import kotlinx.coroutines.launch

class SettingsViewModel(
    val settingsRepository: SettingsRepository
): BaseViewModel<SettingsEvent, SettingsState, SettingsEffect>(initialState = SettingsState.Loading) {

    init {
        viewModelScope.launch {
            viewState = SettingsState.Show(developerMode = false, selectedLanguage = settingsRepository.exportLanguage())
        }
    }

    override fun obtainEvent(event: SettingsEvent) {
        when(val currentState = viewState){
            is SettingsState.Loading -> reduce(event, currentState)
            is SettingsState.Show -> reduce(event, currentState)
        }
    }

    private fun reduce (event: SettingsEvent, currentState: SettingsState.Loading){

    }
    private fun reduce (event: SettingsEvent, currentState: SettingsState.Show){
        when(event){
            is SettingsEvent.DeveloperMode -> {
                Log.d("MyLog", " new value ${event.turnOn}")
                viewState = currentState.copy(developerMode = event.turnOn)
            }
            is SettingsEvent.LanguageSelect -> {
                viewState = currentState.copy(selectedLanguage = event.language)
                settingsRepository.exportLanguage(event.language)
            }
            else -> {}
        }
    }



}