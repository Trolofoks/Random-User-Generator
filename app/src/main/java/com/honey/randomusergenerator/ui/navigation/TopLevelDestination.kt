package com.honey.randomusergenerator.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.honey.randomusergenerator.R

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
    ){
    FAVORITE(
        selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_favorite),
        unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_favorite_outline),
        iconTextId = R.string.favorite,
        titleTextId = R.string.favorite
    ),
    GENERATOR(
        selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_generator),
        unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_generator_outline),
        iconTextId = R.string.generator,
        titleTextId = R.string.app_name
    ),
    EDITOR(
    selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_edit),
    unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_edit_outline),
    iconTextId = R.string.editor,
    titleTextId = R.string.editor
    ),

}

sealed class Icon {
    data class ImageVectorIcon(val imageVector:ImageVector): Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}