package com.honey.randomusergenerator.ui.navigation.navscreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.randomusergenerator.ui.navigation.TopLevelDestination
import com.honey.randomusergenerator.ui.navigation.route.EditorRoute

const val editorNavigationRoute = "editor_route"

fun NavController.navigateToEditor(navOptions: NavOptions? = null){
    this.navigate(editorNavigationRoute, navOptions)
}

fun NavGraphBuilder.editorScreen(onSettingsClick: (String) -> Unit) {
    composable(route = editorNavigationRoute){
        EditorRoute(onSettingsClick = onSettingsClick)
    }
}