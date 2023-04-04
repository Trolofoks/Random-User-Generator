package com.honey.randomusergenerator.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.honey.randomusergenerator.R
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

    val expanded = remember { mutableStateOf(false)}

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "Random Users Generator") },
                actions = {
                    IconButton(onClick = { /* Handle settings icon click */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { expanded.value = !expanded.value }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Open Menu")
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            // Handle "Copy All" menu item click
                            expanded.value = false
                        },
                            text =  {
                                Text(text = "Copy All")
                            },
                            trailingIcon = {
                                Icon(painter = painterResource(id = R.drawable.ic_copy), contentDescription = "Copy All")
                            }
                        )
                    }
                },

            )
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



