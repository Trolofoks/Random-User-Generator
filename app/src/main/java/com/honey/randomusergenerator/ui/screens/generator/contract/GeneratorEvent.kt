package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class GeneratorEvent : ViewEvent{
    data class SaveToFav(val user: User): GeneratorEvent()
    object Regenerate: GeneratorEvent()
    object Generate: GeneratorEvent()
}