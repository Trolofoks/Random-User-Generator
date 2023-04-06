package com.honey.randomusergenerator.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.honey.randomusergenerator.R

//TODO(Learn about enum classes)
enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
    ){
    //TODO(change similar icons and text)
    EDITOR(
        selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_edit),
        unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_edit),
        iconTextId = R.string.editor,
        titleTextId = R.string.editor
    ),
    GENERATOR(
        selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_generator),
        unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_generator),
        iconTextId = R.string.generator,
        titleTextId = R.string.generator
    ),
    FAVORITE(
        selectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_favorite),
        unselectedIcon = Icon.DrawableResourceIcon(R.drawable.ic_favorite),
        iconTextId = R.string.favorite,
        titleTextId = R.string.favorite
    )
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector:ImageVector): Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}