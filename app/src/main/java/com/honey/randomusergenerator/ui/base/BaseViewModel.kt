package com.honey.randomusergenerator.ui.base

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewEffect

const val SIDE_EFFECT_KEY = "non-typical-unique-key"

abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect:ViewEffect>(
    initialState: UiState
): ViewModel() {

    abstract fun obtainEvent(event: Event)

    private val _viewState = MutableStateFlow<UiState>(initialState)
    protected var viewState: UiState
        get() = _viewState.value
        set(value) { _viewState.value = value }

    private val _effect = MutableSharedFlow<Effect?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    protected var effect: Effect?
        get() = _effect.replayCache.last()
        set(value) {_effect.tryEmit(value)}

    fun getEffect(): SharedFlow<Effect?> = _effect.asSharedFlow()
    fun getViewState(): StateFlow<UiState> = _viewState.asStateFlow()



    //TODO(may add return Boolean use .equals)
//    fun setEffect(effect: Effect){
//        viewModelScope.launch { _effect.tryEmit(effect) }
//    }

    //TODO(разберись как это работает, пока что все что я понял, так это так выглядит чтобы не передать state из Editor в Favorite)
//    protected fun setState(reducer: UiState.()-> UiState){
//        val newState = viewState.value.reducer()
//        _viewState.value = newState
//    }

}