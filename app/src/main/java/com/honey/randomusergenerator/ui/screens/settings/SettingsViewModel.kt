package com.honey.randomusergenerator.ui.screens.settings

import androidx.lifecycle.viewModelScope
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEffect
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEvent
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsState
import kotlinx.coroutines.launch

class SettingsViewModel: BaseViewModel<SettingsEvent, SettingsState, SettingsEffect>(initialState = SettingsState.Loading) {

    init {
        viewModelScope.launch {
            viewState = SettingsState.Show(developerMode = false)
        }
    }

    override fun obtainEvent(event: SettingsEvent) {
        when(val currentState = viewState){
            is SettingsState.Loading -> reduce(event, currentState)
            is SettingsState.Show -> reduce(event, currentState)
        }
    }

    private fun reduce (event: SettingsEvent, currentState: SettingsState.Loading){}
    private fun reduce (event: SettingsEvent, currentState: SettingsState.Show){
        when(event){
            is SettingsEvent.DeveloperMode -> {

            }
            is SettingsEvent.LanguageSelect -> {

            }
        }
    }



}