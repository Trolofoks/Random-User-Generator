package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.honey.randomusergenerator.ui.screens.favorite.FavoriteScreen
import com.honey.randomusergenerator.ui.screens.favorite.FavoriteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteScreenDestination(navController: NavController){
    val viewModel = getViewModel<FavoriteViewModel>()

//    FavoriteScreen()
}