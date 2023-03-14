package com.honey.randomusergenerator.ui.screens.favorite

import androidx.compose.runtime.Composable
import com.honey.randomusergenerator.ui.screens.editor.contract.EditorEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

@Composable
fun FavoriteScreen(
    state: FavoriteState,
    effectFlow: FavoriteEffect?,
    onEventSend: (event: FavoriteEvent) -> Unit,
    onNavigationRequested: (navEffect: EditorEffect) -> Unit
) {
    when(state){
        is FavoriteState.EmptyUsersList -> {}
        is FavoriteState.Loading -> {}
        is FavoriteState.ShowUsers -> {}
    }
}