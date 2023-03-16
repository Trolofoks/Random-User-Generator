package com.honey.randomusergenerator.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

@Composable
fun FavoriteScreen(
    state: State<FavoriteState>,
    effect: State<FavoriteEffect?>,
    onEventSend: (event: FavoriteEvent) -> Unit,
    onSettingsClick: (String) -> Unit
) {
    when(val state = state.value){
        is FavoriteState.Empty -> {}
        is FavoriteState.Loading -> {}
        is FavoriteState.ShowFav -> {}
    }

    when(val effect = effect.value){
        is FavoriteEffect.NavBack -> {}
        null -> {}
    }
}