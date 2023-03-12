package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.honey.randomusergenerator.ui.screens.generator.GeneratorScreen
import com.honey.randomusergenerator.ui.screens.generator.GeneratorViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GeneratorScreenDestination(navController: NavController){
    val viewModel = getViewModel<GeneratorViewModel>()

    GeneratorScreen()
}