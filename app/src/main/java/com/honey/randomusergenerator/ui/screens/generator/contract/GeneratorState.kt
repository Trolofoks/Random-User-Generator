package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.randomusergenerator.data.model.Errors
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewState

sealed class GeneratorState :ViewState{
    data class ShowUsers(val users: List<User>) : GeneratorState()
    data class Error(val error: String) : GeneratorState()
    object Generating: GeneratorState()
    object Empty: GeneratorState()
}