package com.honey.randomusergenerator.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.honey.randomusergenerator.R

sealed class Screens(val route: String, @StringRes val stringId: Int, @DrawableRes val iconId: Int){
    object Editor: Screens("editor", R.string.editor, R.drawable.ic_edit)
    object Generator: Screens("generator", R.string.generator, R.drawable.ic_generator)
    object Favorite: Screens("favorite", R.string.favorite, R.drawable.ic_favorite)
}