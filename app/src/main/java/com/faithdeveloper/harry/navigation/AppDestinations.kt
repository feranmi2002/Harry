package com.faithdeveloper.harry.navigation

sealed class AppDestinations(
    val route: String
) {
    object MainScreen : AppDestinations(
        route = "main_screen"
    )

    object DetailsScreen : AppDestinations(
        route = "details_screen"
    )
}
