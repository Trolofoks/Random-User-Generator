package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.honey.randomusergenerator.ui.navigation.navscreen.editorScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.favoriteScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.generatorNavigationRoute
import com.honey.randomusergenerator.ui.navigation.navscreen.generatorScreen

@Composable
fun RugNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = generatorNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier,
    ){
        //TODO(add onBackClick)
        favoriteScreen(onSettingsClick = {})
        generatorScreen(onSettingsClick = {})
        editorScreen(onSettingsClick = {})
    }
}