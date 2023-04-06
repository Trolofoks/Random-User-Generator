package com.honey.randomusergenerator.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.honey.randomusergenerator.R
import androidx.navigation.compose.rememberNavController
import com.honey.data.network.NetworkMonitor
import com.honey.randomusergenerator.ui.navigation.navscreen.editorScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.favoriteScreen
import com.honey.randomusergenerator.ui.navigation.navscreen.generatorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RugApp(
    networkMonitor: NetworkMonitor,
    appState: RugAppState = rememberNyaAppState(networkMonitor = networkMonitor)
) {
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
            //спиздили
            RugBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination,
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevelDestination.Generator.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            editorScreen(onSettingsClick = {})
            generatorScreen(onSettingsClick = {})
            favoriteScreen(onSettingsClick = {})
        }
    }
}

@Composable
private fun RugBottomBar(
    destinations :List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar {

        destinations.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any{it.route?.contains(destination.name,true)?: false} ?: false
            NavigationBarItem(
                selected = selected,
                onClick = {onNavigateToDestination(destination)},
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when(icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = stringResource(id = destination.iconTextId)
                        )
                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = stringResource(id = destination.iconTextId)
                        )
                    }
                },
                label = { Text(stringResource(id = destination.iconTextId)) },
            )
        }
    }
}




