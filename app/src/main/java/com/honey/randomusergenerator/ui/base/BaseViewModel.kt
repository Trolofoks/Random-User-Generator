package com.honey.randomusergenerator.ui.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewEffect

const val SIDE_EFFECT_KEY = "non-typical-unique-key"

abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect:ViewEffect>(
    initialState: UiState
): ViewModel() {

    abstract fun obtainEvent(event: Event)

    //TODO(Learn about "get" and "set" methods)
    private val _viewState = mutableStateOf<UiState>(initialState)
    var viewState: UiState
        get() = _viewState.value
        set(value) { _viewState.value = value }

    private val _event = MutableSharedFlow<Event>()
    val event : SharedFlow<Event> = _event.asSharedFlow()

    private val _effect = MutableSharedFlow<Effect>(replay = 1)//TODO(try to delete replay)
    val effect : SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents(){
        viewModelScope.launch {
            _event.collect(){event->
                obtainEvent(event)
            }
        }
    }

    //TODO(may add return Boolean use .equals)
    fun setEvent(event: Event){
        viewModelScope.launch { _event.tryEmit(event) }
    }
    fun setEffect(effect: Effect){
        viewModelScope.launch { _effect.tryEmit(effect) }
    }

    //TODO(разберись как это работает, пока что все что я понял, так это так выглядит чтобы не передать state из Editor в Favorite)
//    protected fun setState(reducer: UiState.()-> UiState){
//        val newState = viewState.value.reducer()
//        _viewState.value = newState
//    }

}