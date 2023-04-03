package com.honey.randomusergenerator.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.honey.randomusergenerator.ui.navigation.navscreen.editorScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.favoriteScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.generatorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val items = listOf(
        Screens.Favorite,
        Screens.Generator,
        Screens.Editor
    )
    val navController = rememberNavController()

    Scaffold(
        topBar = {
//            TopAppBar {
//
//            }
        },
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(id = screen.iconId),
                                contentDescription = "BottomIcon"
                            )
                        },
                        label = { Text(stringResource(id = screen.stringId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Generator.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            editorScreen(onSettingsClick = {})
            generatorScreen(onSettingsClick = {})
            favoriteScreen(onSettingsClick = {})
        }
    }
}


