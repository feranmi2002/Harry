package com.faithdeveloper.harry.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.ui.details_screen.DetailsRoute
import com.faithdeveloper.harry.ui.main_screen.MainScreenRoute
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun AppNavGraph(
    apiHelper: ApiHelper,
    navController: NavHostController,
    startDestination: String = AppDestinations.MainScreen.route,
    mainScreenViewModel: MainScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(AppDestinations.MainScreen.route) {
            MainScreenRoute(
                mainScreenViewModel = mainScreenViewModel!!,
                onClickCharacter = { character ->
                    mainScreenViewModel?.setSelectedCharacter(character)
                    AppNavigationActions.navigateToDetailsScreen(navController)
                }
            )
        }

        composable(AppDestinations.DetailsScreen.route) { navBackStackEntry ->
            DetailsRoute(
                character = mainScreenViewModel?.selectedCharacter()!!
            ) {
                AppNavigationActions.popBackStack(navController)
            }
        }

    }

}

const val DETAIL_SCREEN_ARG_ID = "details_screen_arg_id"