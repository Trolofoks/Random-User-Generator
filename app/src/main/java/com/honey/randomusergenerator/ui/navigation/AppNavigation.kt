package com.honey.randomusergenerator.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.honey.randomusergenerator.R

@Composable
fun AppNavigation(){
    val items = listOf(
        Screens.Editor,
        Screens.Generator,
        Screens.Favorite
    )
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach{screen->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = screen.iconId), contentDescription = "BottomIcon")},
                        label = { Text(stringResource(id = screen.stringId))},
                        selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) {

                    }
                }
            }
        }
    ) {

    }

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.GENERATOR
    ){
        composable(
            route = Navigation.Routes.GENERATOR
        ){
            GeneratorScreenDestination(navController)
        }
        composable(
            route = Navigation.Routes.EDITOR
        ){
            EditorScreenDestination(navController)
        }
        composable(
            route = Navigation.Routes.FAVORITE
        ){
            FavoriteScreenDestination(navController)
        }
    }
}

object Navigation {
    object Args{
        //user Id or something
    }
    object Routes {
        const val EDITOR = "editor"
        const val GENERATOR = "editor"
        const val FAVORITE = "editor"
    }
}

//example for late use
fun NavController.navigateToEditor(userId: String){
    navigate(route = "${Navigation.Routes.EDITOR}/$userId")
}