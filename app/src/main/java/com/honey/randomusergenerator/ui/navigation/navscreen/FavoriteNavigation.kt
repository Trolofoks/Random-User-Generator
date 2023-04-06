package com.honey.randomusergenerator.ui.navigation.navscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.randomusergenerator.ui.navigation.TopLevelDestination
import com.honey.randomusergenerator.ui.navigation.route.FavoriteRoute

const val favoriteNavigationRoute = "favorite_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null){
    this.navigate(favoriteNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoriteScreen(onSettingsClick: (String)-> Unit){
    composable(route = favoriteNavigationRoute){
        FavoriteRoute(onSettingsClick = onSettingsClick)
    }
}