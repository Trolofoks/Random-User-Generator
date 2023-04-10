package com.honey.randomusergenerator.ui.navigation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.honey.randomusergenerator.ui.navigation.navscreen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

@Composable
fun rememberRugAppState(
    connectionManager: ConnectivityManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): RugAppState{
    return remember(navController,coroutineScope,connectionManager){
        RugAppState(navController, coroutineScope, connectionManager)
    }
}

//TODO(learn about this)
class RugAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    connectivityManager: ConnectivityManager
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

    init {
        coroutineScope.launch {
            observeNetworkConnectivity(connectivityManager)
        }
    }

    private val _isOffline = MutableStateFlow(!isConnected(connectivityManager))
    val isOffline: StateFlow<Boolean> = _isOffline

    private fun observeNetworkConnectivity(connectivityManager: ConnectivityManager) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isOffline.value = false
            }

            override fun onLost(network: Network) {
                _isOffline.value = true
            }
        }
        //??
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        coroutineScope.coroutineContext.job.invokeOnCompletion {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

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

    private fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}