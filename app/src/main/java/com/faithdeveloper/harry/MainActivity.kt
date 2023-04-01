package com.faithdeveloper.harry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.faithdeveloper.harry.data.ApiHelperImpl
import com.faithdeveloper.harry.navigation.AppNavGraph
import com.faithdeveloper.harry.retrofit.ServiceBuilder
import com.faithdeveloper.harry.ui.theme.HarryTheme
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    HarryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val apiHelper = ApiHelperImpl(ServiceBuilder.apiService)
            val navController = rememberNavController()
            val mainScreenViewModel:MainScreenViewModel = viewModel(
                factory = MainScreenViewModel.provideFactory(apiHelper = apiHelper)
            )
            AppNavGraph(
                navController = navController,
                mainScreenViewModel = mainScreenViewModel
            )
        }
    }
}


