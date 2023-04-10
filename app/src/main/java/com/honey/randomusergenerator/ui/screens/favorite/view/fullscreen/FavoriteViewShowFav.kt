package com.honey.randomusergenerator.ui.screens.favorite.view.fullscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.part.BigInfoCardView
import com.honey.randomusergenerator.ui.part.SmallUserCardView

@Composable
fun FavoriteViewShowFav(
    state: FavoriteState.ShowFav,
    fullInfoClick: ((user: User) -> Unit)? = null,
    onHideFullInfo: (() -> Unit)? = null,
    onFavAdd : ((user: User, add: Boolean) -> Unit)? = null
) {
    Surface() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
            LazyColumn(modifier = Modifier.fillMaxWidth(0.95f)){
                for (user in state.users) {
                    item { SmallUserCardView(user = user, onCardClick = {
                        fullInfoClick?.invoke(it)
                    }) }
                }
            }
            state.fullInfoUser?.let{fullUser->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.transparent_black))
                    .clickable {
                        onHideFullInfo?.invoke()
                    },
                    contentAlignment = Alignment.Center
                ) {
                    BackHandler(onBack = {
                        onHideFullInfo?.invoke()
                    })
                    BigInfoCardView(user = fullUser, inFavChecked = true ,favorite = { user, fav ->
                        onFavAdd?.invoke(user, fav)
                    }, modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .clickable(enabled = false) {},
                        exportLanguageFormat = state.exportLanguage
                    )
                }
            }
        }
    }
}