package com.faithdeveloper.harry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.faithdeveloper.harry.model.Characters
import com.google.gson.Gson

object AppNavigationActions {
    private fun cleanupNavGraph(
        navOptionsBuilder: NavOptionsBuilder
    ) {
        navOptionsBuilder.launchSingleTop = true
        navOptionsBuilder.restoreState = true
    }

    val navigateToDetailsScreen: (navController: NavController, character: Characters) -> Unit =
        { navController, character ->
            navController.navigate(
                AppDestinations.DetailsScreen.route + "/${
                    Gson().toJson(
                        character, Characters::class.java
                    )
                }"
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
