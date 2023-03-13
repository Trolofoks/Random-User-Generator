package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.honey.randomusergenerator.ui.screens.editor.EditorScreen
import com.honey.randomusergenerator.ui.screens.editor.EditorViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditorScreenDestination(navController: NavController){
    val viewModel = getViewModel<EditorViewModel>()
    EditorScreen(
        state = viewModel.viewState
    )
}