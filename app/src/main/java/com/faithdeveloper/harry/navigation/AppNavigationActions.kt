package com.faithdeveloper.harry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

object AppNavigationActions {
    private fun cleanupNavGraph(
        navOptionsBuilder: NavOptionsBuilder
    ) {
        navOptionsBuilder.launchSingleTop = true
        navOptionsBuilder.restoreState = true
    }

    val navigateToDetailsScreen: (navController: NavController) -> Unit =
        { navController ->
            navController.navigate(
                AppDestinations.DetailsScreen.route
            ) {
                navController.graph.startDestinationRoute?.let { route ->
                    cleanupNavGraph(this)
                }
            }


        }

    val popBackStack: (navController: NavController) -> Unit = { navController ->
        navController.popBackStack()
    }
}
