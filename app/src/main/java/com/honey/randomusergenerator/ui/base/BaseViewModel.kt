package com.honey.randomusergenerator.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewEffect

const val SIDE_EFFECT_KEY = "non-typical-unique-key"

abstract class BaseViewModel<Event: ViewEvent, UiState:ViewState, Effect:ViewEffect>: ViewModel() {

    abstract fun setInitialState(): UiState
    abstract fun obtainEvent(event: Event)

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    private val _event = MutableSharedFlow<Event>()
    val event : SharedFlow<Event> = _event.asSharedFlow()

    private val _effect = MutableSharedFlow<Effect>(extraBufferCapacity = 1)//TODO(try to delete capacity)
    val effect : SharedFlow<Effect> = _effect.asSharedFlow()

    init {

    }

    //TODO(may add return Boolean use .equals)
    fun setEvent(event: Event){
        viewModelScope.launch { _event.tryEmit(event) }
    }
    fun setEffect(effect: Effect){
        viewModelScope.launch { _effect.tryEmit(effect) }
    }

    //TODO(разберись как это работает, пока что все что я понял, так это так выглядит чтобы не передать state из Editor в Favorite)
    protected fun setState(reducer: UiState.()-> UiState){
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }
}