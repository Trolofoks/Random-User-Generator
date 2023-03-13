package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.randomusergenerator.data.model.Errors
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewState
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

sealed class GeneratorState :ViewState{
    data class ShowGeneratedUsers(val users: List<User>) : GeneratorState()
    data class ShowError(val error: Errors) : GeneratorState()
    object Generating: GeneratorState()
}