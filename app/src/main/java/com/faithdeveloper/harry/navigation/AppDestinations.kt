package com.faithdeveloper.harry.navigation

import androidx.annotation.StringRes
import com.faithdeveloper.harry.R

sealed class AppDestinations(
    val route:String,
    @StringRes val destinationTitle: Int
){
    object MainScreen:AppDestinations(
        route = "main_screen",
        destinationTitle = R.string.app_name
    )

    object DetailsScreen:AppDestinations(
        route = "details_screen",
        destinationTitle = R.string.app_name
    )
}
