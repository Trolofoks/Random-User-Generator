package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.randomusergenerator.ui.base.ViewEffect

sealed class GeneratorEffect : ViewEffect {
    object NavBack: GeneratorEffect()
}