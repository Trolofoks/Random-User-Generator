package com.honey.randomusergenerator.ui.navigation

import android.net.ConnectivityManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.data.model.Holder
import com.honey.randomusergenerator.ui.navigation.route.SettingsDialogRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RugApp(
    connectionManager: ConnectivityManager,
    appState: RugAppState = rememberRugAppState(connectionManager = connectionManager)
) {

    val snackbarHostState = remember { SnackbarHostState() }
    Holder.snackbarHostState = snackbarHostState

    if (appState.shouldShowSettingsDialog){
        SettingsDialogRoute {
            appState.setShowSettingsDialog(false)
        }
    }

    val isOffline = appState.isOffline.collectAsState()

    val offlineText = stringResource(id = R.string.no_internet)
    LaunchedEffect(isOffline.value) {
        if (isOffline.value) {
            snackbarHostState.showSnackbar(
                message = offlineText,
                duration = SnackbarDuration.Indefinite
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            val destination = appState.currentTopLevelDestination
            if (destination != null) {
                TopAppBar(
                    title = { Text(text = stringResource(id = destination.titleTextId)) },
                    actions = {
                        IconButton(onClick = { appState.setShowSettingsDialog(true) }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Settings")
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
        },
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            RugBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination,
            )
        }
    ) { innerPadding ->
        RugNavHost(
            navController = appState.navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
private fun RugBottomBar(
    destinations :List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
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




