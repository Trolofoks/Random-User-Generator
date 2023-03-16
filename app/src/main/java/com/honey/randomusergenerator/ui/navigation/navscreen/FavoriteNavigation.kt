package com.honey.randomusergenerator.ui.navigation.navscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.randomusergenerator.ui.navigation.Screens
import com.honey.randomusergenerator.ui.navigation.route.FavoriteRoute

fun NavController.navigateToFavorite(navOptions: NavOptions? = null){
    this.navigate(Screens.Favorite.route, navOptions)
}

fun NavGraphBuilder.favoriteScreen(onSettingsClick: (String)-> Unit){
    composable(route = Screens.Favorite.route){
        FavoriteRoute(onSettingsClick = onSettingsClick)
    }
}