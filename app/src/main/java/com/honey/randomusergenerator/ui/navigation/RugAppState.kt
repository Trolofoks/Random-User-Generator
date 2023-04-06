package com.honey.randomusergenerator.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.honey.data.network.NetworkMonitor
import com.honey.randomusergenerator.ui.navigation.navscreen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberNyaAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): RugAppState{
    return remember(navController,coroutineScope,networkMonitor){
        RugAppState(navController, coroutineScope, networkMonitor)
    }
}

//TODO(learn about this)
class RugAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when(currentDestination?.route){
            editorNavigationRoute -> TopLevelDestination.EDITOR
            favoriteNavigationRoute -> TopLevelDestination.FAVORITE
            generatorNavigationRoute -> TopLevelDestination.GENERATOR
            else -> null
        }

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set

    val isOffline = networkMonitor.isOnline
            //TODO(эта чо)
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(50000),
            initialValue = false
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination){
        //TODO(move under trace "androidx.tracing")
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination){
            TopLevelDestination.EDITOR -> navController.navigateToEditor(topLevelNavOptions)
            TopLevelDestination.FAVORITE -> navController.navigateToFavorite(topLevelNavOptions)
            TopLevelDestination.GENERATOR -> navController.navigateToGenerator(topLevelNavOptions)
        }
    }
    fun setShowSettingsDialog(shouldShow: Boolean){
        shouldShowSettingsDialog = shouldShow
    }
}