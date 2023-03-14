package com.honey.randomusergenerator.GDFDFG

import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.base.ViewEffect
import com.honey.randomusergenerator.ui.base.ViewEvent
import com.honey.randomusergenerator.ui.base.ViewState

inline fun <reified S : ViewState, reified E : ViewEvent, reified F : ViewEffect> BaseViewModel<E, S, F>.generateReduceFunctions() {
    val stateClass = S::class.java
    val eventClass = E::class.java

    stateClass.declaredClasses.forEach { stateSubclass ->
        val reduceFunctionName = "reduce"
        val reduceFunctionParams = listOf(eventClass, stateSubclass)

        try {
            val reduceFunction = this::class.java.getDeclaredMethod(reduceFunctionName, *reduceFunctionParams.toTypedArray())
            reduceFunction.isAccessible = true
        } catch (e: NoSuchMethodException) {
            val errorMessage = "No reduce function found for state subclass ${stateSubclass.simpleName}"
            throw IllegalStateException(errorMessage, e)
        }
    }
}