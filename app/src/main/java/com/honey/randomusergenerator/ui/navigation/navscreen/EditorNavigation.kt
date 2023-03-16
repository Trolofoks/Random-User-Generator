package com.honey.randomusergenerator.ui.navigation.navscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.randomusergenerator.ui.navigation.Screens
import com.honey.randomusergenerator.ui.navigation.route.EditorRoute

fun NavController.navigateToEditor(navOptions: NavOptions? = null){
    this.navigate(Screens.Editor.route, navOptions)
}

fun NavGraphBuilder.editorScreen(onSettingsClick: (String) -> Unit) {
    composable(route = Screens.Editor.route){
        EditorRoute(onSettingsClick = onSettingsClick)
    }
}