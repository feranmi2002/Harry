package com.faithdeveloper.harry.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.model.Characters
import com.faithdeveloper.harry.ui.details_screen.DetailsScreenRoute
import com.faithdeveloper.harry.ui.main_screen.MainScreenRoute
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel
import com.google.gson.Gson

@Composable
fun AppNavGraph(
    apiHelper: ApiHelper,
    navController: NavHostController,
    startDestination: String = AppDestinations.MainScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(AppDestinations.MainScreen.route) {
            val mainScreenViewModel: MainScreenViewModel = viewModel(
                factory = MainScreenViewModel.provideFactory(apiHelper = apiHelper)
            )

            MainScreenRoute(
                mainScreenViewModel = mainScreenViewModel,
                onClickCharacter = { character ->
                    AppNavigationActions.navigateToDetailsScreen(navController, character)
                }
            )
        }

        composable(
            AppDestinations.DetailsScreen.route + "/{$DETAIL_SCREEN_ARG_ID}",
            arguments = listOf(navArgument(DETAIL_SCREEN_ARG_ID) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            DetailsScreenRoute(
                character = Gson().fromJson(
                    navBackStackEntry.arguments?.getString(
                        DETAIL_SCREEN_ARG_ID
                    )!!, Characters::class.java
                )
            ) {
                AppNavigationActions.popBackStack(navController)
            }
        }

    }

}

const val DETAIL_SCREEN_ARG_ID = "details_screen_arg_id"