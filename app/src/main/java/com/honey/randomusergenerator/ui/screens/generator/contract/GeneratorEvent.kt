package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class GeneratorEvent : ViewEvent{
    data class Favorite(val user: User, val add: Boolean): GeneratorEvent()
    data class Regenerate(val amount: Int): GeneratorEvent()
    data class Generate(val amount: Int): GeneratorEvent()
    data class FullInfoClick(val user:User): GeneratorEvent()
    object HideFullInfo : GeneratorEvent()
    object Refresh: GeneratorEvent()
}