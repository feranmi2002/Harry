package com.faithdeveloper.harry.ui.main_screen

import androidx.compose.runtime.Composable
import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel

@Composable
fun MainScreenRoute(
    mainScreenViewModel: MainScreenViewModel,
    onClickCharacter: (character: HarryCharacter) -> Unit
) {
    MainScreen(
//        character = mainScreenViewModel._result,
        onClickCharacter = onClickCharacter,
        newSearch = { query ->
            mainScreenViewModel.newSearch(query)
        },
        mainScreenViewModel,
        onSearchTypeChange = {
            mainScreenViewModel.setSearchType(it)
        }) {
        mainScreenViewModel.retry()
    }
}