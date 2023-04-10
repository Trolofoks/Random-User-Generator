package com.honey.randomusergenerator.di

import com.honey.randomusergenerator.ui.screens.editor.EditorViewModel
import com.honey.randomusergenerator.ui.screens.favorite.FavoriteViewModel
import com.honey.randomusergenerator.ui.screens.generator.GeneratorViewModel
import com.honey.randomusergenerator.ui.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GeneratorViewModel(
            randomRepository = get(),
            savedRepository = get(),
            settingsRepository = get()
        )
    }
    viewModel {
        FavoriteViewModel(
            savedRepository = get(),
            settingsRepository = get()
        )
    }
    viewModel {
        EditorViewModel(
            randomRepository = get(),
            savedRepository = get()
        )
    }
    viewModel {
        SettingsViewModel(
            settingsRepository = get()
        )
    }
}