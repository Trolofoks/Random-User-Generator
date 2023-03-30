package com.honey.randomusergenerator.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState
import com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen.FavoriteViewEmpty
import com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen.FavoriteViewLoading
import com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen.FavoriteViewShowFav

@Composable
fun FavoriteScreen(
    state: State<FavoriteState>,
    effect: State<FavoriteEffect?>,
    onEventSend: (event: FavoriteEvent) -> Unit,
    onSettingsClick: (String) -> Unit
) {
    when(val state = state.value){
        is FavoriteState.Empty -> {
            FavoriteViewEmpty(state = state, retryLoad = {})
        }
        is FavoriteState.Loading -> {
            FavoriteViewLoading(state = state)
        }
        is FavoriteState.ShowFav -> {
            FavoriteViewShowFav(
                state = state,
                fullInfoClick = {user ->
                    onEventSend(FavoriteEvent.FullInfo(user))
                },
                onHideFullInfo = {
                    onEventSend(FavoriteEvent.HideFullInfo)
                },
                onFavAdd = { user, add ->
                    onEventSend(FavoriteEvent.Favorite(user, add))
                }
            )
        }
    }

    when(val effect = effect.value){
        is FavoriteEffect.NavBack -> {}
        null -> {}
    }
}