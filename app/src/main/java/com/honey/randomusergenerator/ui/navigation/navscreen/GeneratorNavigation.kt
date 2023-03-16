package com.honey.randomusergenerator.ui.navigation.navscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.randomusergenerator.ui.navigation.Screens
import com.honey.randomusergenerator.ui.navigation.route.GeneratorRoute

fun NavController.navigateToGenerator(navOptions: NavOptions? = null){
    this.navigate(Screens.Generator.route,navOptions)
}

fun NavGraphBuilder.generatorScreen(onSettingsClick: (String)-> Unit){
    composable(route = Screens.Generator.route){
        GeneratorRoute(onSettingsClick = onSettingsClick)
    }
}