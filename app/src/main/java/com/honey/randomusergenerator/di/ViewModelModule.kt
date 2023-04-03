package com.honey.randomusergenerator.di

import com.honey.randomusergenerator.ui.screens.editor.EditorViewModel
import com.honey.randomusergenerator.ui.screens.favorite.FavoriteViewModel
import com.honey.randomusergenerator.ui.screens.generator.GeneratorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GeneratorViewModel(
            randomRepository = get(),
            savedRepository = get()
        )
    }
    viewModel {
        FavoriteViewModel(
            savedRepository = get()
        )
    }
    viewModel {
        EditorViewModel(
            randomRepository = get(),
            savedRepository = get()
        )
    }
}