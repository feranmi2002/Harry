package com.faithdeveloper.harry.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faithdeveloper.harry.ui.details_screen.DetailsRoute
import com.faithdeveloper.harry.ui.main_screen.MainScreenRoute
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = AppDestinations.MainScreen.route,
    mainScreenViewModel: MainScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {

//        main screen
        composable(AppDestinations.MainScreen.route) {
            MainScreenRoute(
                mainScreenViewModel = mainScreenViewModel,
                onClickCharacter = { character ->

//                    store the character clicked by user in the view model

                    mainScreenViewModel.setSelectedCharacter(character)

//                    pass the character into the detail screen

                    AppNavigationActions.navigateToDetailsScreen(navController)
                }
            )
        }

//        detail screen
        composable(AppDestinations.DetailsScreen.route) { navBackStackEntry ->
            DetailsRoute(
                character = mainScreenViewModel.selectedCharacter()
            ) {
//                pop to main screen
                AppNavigationActions.popBackStack(navController)
            }
        }

    }
}