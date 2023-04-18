package com.honey.randomusergenerator.ui.screens.generator.contract

import com.honey.data.settings.model.CopyType
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewState

sealed class GeneratorState :ViewState{
    data class ShowUsers(val users: List<User>, val selectedUser: User? = null, val exportCopyType: CopyType = CopyType.EDITED) : GeneratorState()
    object Generating: GeneratorState()
    object Empty: GeneratorState()
}