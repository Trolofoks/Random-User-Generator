package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GeneratorViewEmpty(
    modifier: Modifier = Modifier,
    onReloadClick: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {

    }
}