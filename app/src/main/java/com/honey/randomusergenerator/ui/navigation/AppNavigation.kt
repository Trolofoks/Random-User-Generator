package com.honey.randomusergenerator.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.honey.randomusergenerator.ui.navigation.destination.EditorScreenDestination

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
                    )
                }
            }
        }
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screens.Generator.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(
                route = Screens.Generator.route
            ){
                GeneratorScreenDestination(navController)
            }
            composable(
                route = Screens.Editor.route
            ){
                EditorScreenDestination(navController)
            }
            composable(
                route = Screens.Favorite.route
            ){
                FavoriteScreenDestination(navController)
            }
        }
    }
}


