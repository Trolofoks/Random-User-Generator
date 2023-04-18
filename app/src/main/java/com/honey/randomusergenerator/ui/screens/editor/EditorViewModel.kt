package com.honey.randomusergenerator.ui.screens.editor

import androidx.lifecycle.viewModelScope
import com.honey.data.random.RandomRepository
import com.honey.data.saved.SavedRepository
import com.honey.data.random.model.GenerateKeys
import com.honey.randomusergenerator.extensions.saveToRepo
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEvent
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorState
import kotlinx.coroutines.launch

class EditorViewModel(
    private val randomRepository: RandomRepository,
    private val savedRepository: SavedRepository
):BaseViewModel<EditorEvent, EditorState, EditorEffect>(
    initialState = EditorState.Loading
) {
    init {
        setNewByKey(GenerateKeys.AVATAR)
    }

    override fun obtainEvent(event: EditorEvent) {
        when(val state = viewState){
            is EditorState.ShowEditor -> {reduce(event, state)}
            is EditorState.Saving -> {reduce(event, state)}
            is EditorState.Loading -> {reduce(event, state)}
        }
    }

    private fun reduce(event: EditorEvent, currentState: EditorState.ShowEditor){
        when(event){
            is EditorEvent.GenerateByKey -> { setNewByKey(event.GenerateKey, currentState = currentState) }
            is EditorEvent.TryToSave -> {
                viewModelScope.launch {
                    event.user.saveToRepo(savedRepository)
                }
            }
            else -> {}
        }
    }
    private fun reduce(event: EditorEvent, currentState: EditorState.Saving){

    }
    private fun reduce(event: EditorEvent, currentState: EditorState.Loading){

    }

    private fun setNewByKey(Key: String, currentState: EditorState.ShowEditor = EditorState.ShowEditor()){
        viewModelScope.launch {
            viewState = when(Key){
                GenerateKeys.AVATAR -> {currentState.copy(avatarUrl = randomRepository.getByKey(Key))}
                GenerateKeys.NAME -> {currentState.copy(name = randomRepository.getByKey(Key))}
                GenerateKeys.EMAIL -> {currentState.copy(email = randomRepository.getByKey(Key))}
                GenerateKeys.BIRTHDAY -> {currentState.copy(birthday = randomRepository.getByKey(Key))}
                GenerateKeys.ADDRESS -> {currentState.copy(address = randomRepository.getByKey(Key))}
                GenerateKeys.NUMBER -> {currentState.copy(number = randomRepository.getByKey(Key))}
                else -> {viewState}
            }
        }
    }

}